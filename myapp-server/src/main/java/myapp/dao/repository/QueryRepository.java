package myapp.dao.repository;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import myapp.dao.stub.IQueryRepository;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.PostConstruct;

@Repository
public class QueryRepository implements IQueryRepository {

    private static final Logger LOG = LoggerFactory.getLogger(QueryRepository.class);
    
    private static XPath xpath = XPathFactory.newInstance().newXPath();
    
    private  Map<String, String> queryMap = new HashMap<>();

    @PostConstruct
    public void initIt() throws Exception {
        LOG.debug("inside post construct initializing query cache");
        initialize();
    }

    private void initialize() throws Exception {
        try {
            LOG.debug("Creating xml objects");
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true); // support for xml-namespace,
                                                // default is false!
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            ClassLoader cl = this.getClass().getClassLoader(); 
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(
                    cl);
            LOG.debug("Getting resources xml objects");
            Resource[] resources = resolver
                    .getResources("classpath*:queries/*SQL.xml");

            if (resources.length == 0) {
                LOG.error("No query files found!!!");
                throw new RuntimeException("No query files found!!!");
            }
            
            for (Resource resource : resources) {
                Document doc = builder.parse(resource.getInputStream());
                LOG.debug("Processing query file:{}", resource.getFilename());

                XPathExpression expr = xpath.compile("/SqlQueries/SqlQuery");
                NodeList list = (NodeList) expr.evaluate(doc,
                        XPathConstants.NODESET);

                for (int i = 0; i < list.getLength(); i++) {
                    Node node = list.item(i);
                    if (node.getAttributes().getNamedItem("name") != null) {
                        queryMap.put(node.getAttributes().getNamedItem("name")
                                .getNodeValue(), node.getTextContent().trim());
                    }
                }
            }
        } catch (Exception ex) {
            LOG.error("Error while initializing query helper:", ex);
            throw ex;
        }
    }

    public boolean isInitialized() {
        return (queryMap != null && !queryMap.isEmpty());
    }

    private  void printAll() {
        for (Map.Entry<String, String> entry : queryMap.entrySet()) {
            LOG.debug("{}::{} ", entry.getKey(), entry.getValue());
        }
    }

    public String getQuery(String qryName) {
        LOG.debug("Retreiving query for name:{}:", qryName);
        String qry = queryMap.get(qryName);
        if (StringUtils.isBlank(qry)) {
            throw new RuntimeException("Query not found in queries cache:"
                    + qryName);
        } else {
            return qry;
        }

    }

}

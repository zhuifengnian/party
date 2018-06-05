package com.hust.party.common;


import com.hust.party.pojo.Activity;
import com.hust.party.pojo.Enterprise;
import com.hust.party.service.EnterpriseService;
import com.hust.party.vo.ActivityEnterpriseVo;
import com.hust.party.vo.ActivityVo;
import com.hust.party.vo.PerenceActivityVO;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


public class SolrUtil {

    /**
     *
     * @throws IOException
     * @throws SolrServerException
     */
    @Autowired
    private EnterpriseService enterpriseService;
    public  void insertActivitySolr(Integer id , Activity activity)  throws IOException, SolrServerException {

        HttpSolrClient server = new HttpSolrClient("http://47.106.167.38:8090/solr/party_activity");
        server.setConnectionTimeout(300000);//连接超时时间5分钟
        server.setParser(new XMLResponseParser()); // binary parser is used by default
        server.setSoTimeout(1000); // socket read timeout
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false); // defaults to false
        server.setAllowCompression(true);

        SolrInputDocument input = new SolrInputDocument();
        SolrQuery solrQuery=new SolrQuery();
        input.addField("id",id);
        input.addField("enterprise_id" ,activity.getEnterpriseId());
        input.addField("word",activity.getWord());
        input.addField("picture",activity.getPicture());
        input.addField("video",activity.getVideo());
        input.addField("feature",activity.getFeature());
        input.addField("contain_people",activity.getContainPeople());
        input.addField("preferential_price",activity.getPreferentialPrice());
        input.addField("original_price",activity.getOriginalPrice());
        input.addField("address",activity.getAddress());
        input.addField("activity_time",activity.getActivityTime());
        input.addField("minu_people",activity.getMinuPeople());
        input.addField("address_name",activity.getAddressName());
        input.addField("longitude",activity.getLongitude());
        input.addField("latitude",activity.getLatitude());
        input.addField("favourable",activity.getFavourable());
        input.addField("classify",activity.getClassify());
        input.addField("arrive_time",activity.getActivityTime());
        input.addField("category",activity.getCategory());
        input.addField("copies",activity.getCopies());
        input.addField("arrive_copies",activity.getArriveCopies());
        input.addField("state",activity.getState());
        input.addField("title",activity.getTitle());
        server.add(input);
        server.commit();
        server.close();


    }
    public   SolrDocumentList  getActivitySolr(String qw,int start)  throws IOException, SolrServerException {

        HttpSolrClient server = new HttpSolrClient("http://47.106.167.38:8090/solr/party_activity");
        server.setConnectionTimeout(300000);//连接超时时间5分钟
        server.setParser(new XMLResponseParser()); // binary parser is used by default
        server.setSoTimeout(1000); // socket read timeout
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false); // defaults to false
        server.setAllowCompression(true);
        SolrQuery solrQuery=new SolrQuery();
        ModifiableSolrParams solrParams = new ModifiableSolrParams();
         qw=qw+"*";
        solrQuery.setQuery("state:1");
        solrQuery.setQuery("title:"+"*"+qw+"*");
        solrQuery.setStart(start);
        solrQuery.setRows(10);
        QueryResponse queryResponse = server.query(solrQuery);

        SolrDocumentList results = queryResponse.getResults();



return results;
    }


}

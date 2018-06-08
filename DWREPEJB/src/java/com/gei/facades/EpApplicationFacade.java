/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.facades;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.gei.entities.EpApplication;
import com.gei.entities.EpCoordinate;
import com.gei.entities.EpLocation;
import com.gei.entities.FiAgency;
import com.gei.entities.LiStatusLookup;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.json.JSONObject;

/**
 *
 * @author ymei
 */
@Stateless
public class EpApplicationFacade extends AbstractFacade<EpApplicationFacade,EpApplication> {
    @PersistenceContext(unitName = "DWREPEJBPU",type = PersistenceContextType.EXTENDED)
    @Override
    public EpApplicationFacade setEntityManager(EntityManager em)
    {
        entityManager = em;
        return this;
    }

    public EpApplicationFacade() {
        super(EpApplication.class);
    }
    
    @Override
    public String getPersistenceUnitName() {return "DWREPEJBPU";}
    
    public static void main(String[] args) throws IOException, JsonMappingException, JsonGenerationException 
    {
        EpApplicationFacade eaf = new EpApplicationFacade();
        List<EpApplication> eps = null;
//        List<EpApplication> eps = eaf.findAll();
//        
//        for(EpApplication ep: eps){
//            System.out.println(ep);
//        }
        
//        EpApplication ep = eaf.find(2080);
//        FiAgency fiAgency = new FiAgency(0);
//        System.out.println(fiAgency);
//        System.out.println(fiAgency.getFiAgencyName());
//        
//        FiAgency fiAgency2 = new FiAgency(2);
//        System.out.println(fiAgency2.getFiAgencyName());
//        
//        LiStatusLookup status = new LiStatusLookup(4);
//        System.out.println(status.getLiStatusDesc());
        
        EpApplication ep = eaf.find(21421);
//        System.out.println(ep.getLiPermitCommentsCollection());
//        System.out.println(ep.getLiPermitStatusCollection());
        System.out.println(ep.getLiPermitContactsCollection());
        eps = eaf.select("SELECT * FROM EP_APPLICATION WHERE EP_NO LIKE '12257%'", com.gei.entities.EpApplication.class);
//        JSONObject jsonObj = new JSONObject(ep.toMap());
//        System.out.println(jsonObj);
        
//        Collection<EpLocation> locations = ep.getEpLocationCollection();
//        System.out.println(locations.size()); 
//        Collection<EpCoordinate> coordinate = ep.getEpCoordinateCollection();
//        System.out.println(coordinate.size()); 
        
        ObjectMapper mapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        mapper.getSerializationConfig().setDateFormat(dateFormat);
        // Remove NULL attributes
//        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);        
        mapper.enable(Feature.INDENT_OUTPUT);
//        System.out.println(mapper.writeValueAsString(eps));
//        System.out.println(mapper.writeValueAsString(ep));

        // convert ep object to json string, and save to a file
        mapper.writeValue(new File("c:\\tmp\\dwr_ep.json"), ep);       
    }    
}

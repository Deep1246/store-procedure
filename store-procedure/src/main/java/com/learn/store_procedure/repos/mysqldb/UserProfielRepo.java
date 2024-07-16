package com.learn.store_procedure.repos.mysqldb;

import com.learn.store_procedure.model.mysql.Personalinfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserProfielRepo {


    @PersistenceContext(unitName = "mysql")
    private EntityManager entityManager;


    public List<Personalinfo> getUserProfilesById(String id){

        List<Personalinfo> list = new ArrayList<>();

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("GetPersonalinfoById");

        //register input parameter
        storedProcedureQuery.registerStoredProcedureParameter(1,String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter(1,id);

        //register output paramater

        storedProcedureQuery.execute();

        List<Object[]> result = storedProcedureQuery.getResultList();

        list= result.stream().map(
                row-> {
                    Personalinfo up = new Personalinfo();
                    up.setId(String.valueOf(row[0]));
                    up.setFirstName((String) row[1]);
                    up.setLastName((String) row[2]);
                    up.setEmail((String) row[3]);
                    up.setBirthDate((java.sql.Date)  row[4]);

                    up.setSex(new BigDecimal(row[5].toString()).longValue() );

                    up.setCreated_by((String) row[6]);
                    up.setCreateDate(row[7].toString());
                    up.setUpdateBy((String) row[8]);
                    up.setUpdateDate(row[9].toString());
                    return up;
                })
                .collect(Collectors.toList());
        return list;
    }


}

package com.orika.study.run;

import com.orika.study.dto.PersonA;
import com.orika.study.dto.PersonACopyList;
import com.orika.study.dto.PersonAList;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hank on 17-11-27.
 */
public class ManyToMany {

    public static final MapperFactory mapperFactory;
    public static final MapperFacade mapper;

    static {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }

    public static void main(String[] args) {
        new ManyToMany().arrayToArraySceneTwo();
    }

    //数组元素类型不一致时，即使元素内属性名一致也是无法转换的 实际时可以转的
    public void arrayToArraySceneOne() {
        mapperFactory.classMap(PersonAList.class, PersonACopyList.class);
        PersonAList personAList = new PersonAList();
        PersonA personA = new PersonA();
        personA.setAgeA("agea");
        personA.setNameA("namea");
        personA.setSexid("sexid");

        List<PersonA> alist = new ArrayList<PersonA>();
        alist.add(personA);
        personAList.setPersonAList(alist);

        PersonACopyList personACopyList = mapper.map(personAList, PersonACopyList.class);
        System.out.println("");

    }

    //数组元素类型 如果数据元素中出现某个字段需要手动映射，那么需要其它字段也需要映射，即使名字一样；
    public void arrayToArraySceneTwo() {
//        mapperFactory.classMap(PersonAList.class, PersonACopyList.class)
//                .field("personAList{sexid}", "personAList{sexNo}")
//                .field("personAList{ageA}","personAList{ageA}")
//                .field("personAList{nameA}","personAList{nameA}")
//                .byDefault()
//                .register();

        mapperFactory.classMap(PersonAList.class, PersonACopyList.class)
                .field("personAList{sexid}", "personAList{sexNo}")
                .byDefault()
                .register();

        PersonAList personAList = new PersonAList();
        PersonA personA = new PersonA();
        personA.setAgeA("agea");
        personA.setNameA("namea");
        personA.setSexid("sexid");

        List<PersonA> alist = new ArrayList<PersonA>();
        alist.add(personA);
        personAList.setPersonAList(alist);

        PersonACopyList personACopyList = mapper.map(personAList, PersonACopyList.class);
        System.out.println("");

    }
}

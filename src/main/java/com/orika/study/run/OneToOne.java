package com.orika.study.run;

import com.orika.study.dto.PersonA;
import com.orika.study.dto.PersonB;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by hank on 17-11-14.
 */
public class OneToOne {

    public static void main(String[] args) {

        //创建mapperFactory
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        //注册
        mapperFactory.classMap(PersonA.class, PersonB.class)
                .field("nameA","nameB")
                .field("ageA","ageB")
                .byDefault()//其它的字段默认匹配
                .register();

        //获取mapperFacade
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        //创建personA实例
        PersonA personA = new PersonA();
        personA.setNameA("Tom");
        personA.setAgeA("39");

        //映射生成personB
        PersonB personB = mapperFacade.map(personA,PersonB.class);

        //验证
        System.out.println(personB.getAgeB());
        System.out.println(personB.getAgeB());


    }

}

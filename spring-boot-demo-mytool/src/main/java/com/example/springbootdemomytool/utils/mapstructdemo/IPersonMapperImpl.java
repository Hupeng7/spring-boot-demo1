package com.example.springbootdemomytool.utils.mapstructdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName IPersonMapperImpl
 * @Description
 * @Author H
 * @Date 2022/10/10 14:11
 * @Version 1.0
 */
public class IPersonMapperImpl implements PersonMapper {
    public IPersonMapperImpl() {
    }

    @Override
    public PersonDTO conver(Person person) {
        if (person == null) {
            return null;
        } else {
            PersonDTO personDTO = new PersonDTO();
            if (person.getDescribe() != null) {
                personDTO.setDescribe(person.getDescribe());
            } else {
                personDTO.setDescribe("默认值");
            }
            if (person.getId() != null) {
                personDTO.setId(Long.parseLong(person.getId()));
            }

            personDTO.setPersonName(person.getName());
            personDTO.setAge(String.valueOf(person.getAge()));
            if (person.getSource() != null) {
                personDTO.setSource(person.getSource().toString());
            }

            if (person.getCreateTime() != null) {
                personDTO.setCreateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(person.getCreateTime()));
            }

            personDTO.setHeight(String.valueOf(person.getHeight()));
            return personDTO;
        }
    }

    public static Date getDateFormat(Date date, String format) {
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (Exception e) {
            return null;
        }
    }
}

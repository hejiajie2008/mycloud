package com.cloud.web.config;

import com.cloud.api.vo.UserVo;
import com.cloud.web.vo.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

@Configuration
public class ConvertConfig implements WebMvcConfigurer {

    class PropertisHttpMessageConverter extends AbstractHttpMessageConverter<Person> {

        public PropertisHttpMessageConverter(){
            super(MediaType.valueOf("application/test+person"));
            setDefaultCharset(Charset.forName("UTF-8"));
        }

        @Override
        protected boolean supports(Class<?> clazz) {
            return clazz.isAssignableFrom(Person.class);
        }

        @Override
        protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

            Properties properties=new Properties();
            properties.load(inputMessage.getBody());
            Person person=new Person();
            person.setId(Long.valueOf(properties.getProperty("person.id")));
            person.setUsername(String.valueOf(properties.getProperty("person.username")));
            return person;
        }

        @Override
        protected void writeInternal(Person person, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

            Properties properties=new Properties();


            properties.setProperty("person.id",String.valueOf(person.getId()));
            properties.setProperty("person.username",person.getUsername());
            properties.store(new OutputStreamWriter(outputMessage.getBody(),getDefaultCharset()),"out");
        }
    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0,new PropertisHttpMessageConverter());
    }
}

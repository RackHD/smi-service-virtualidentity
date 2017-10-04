/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.virtualidentity.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.dell.isg.smi.virtualidentity.Application;
import com.dell.isg.smi.virtualidentity.ApplicationStartup;

import springfox.documentation.staticdocs.Swagger2MarkupResultHandler;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource("classpath:application-test.properties")
public class Swagger2MarkupTest {

	private static final String API_URI = "/v2/api-docs?group=virtualidentity";

	@MockBean
	ApplicationStartup applicationStartup;
	
	
	@Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() throws IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void convertSwaggerToAsciiDoc() throws Exception {
    	String outputDir = System.getProperty("staticdocs.outputDir");
        Swagger2MarkupResultHandler.Builder builder = Swagger2MarkupResultHandler
            .outputDirectory(outputDir);
        mockMvc.perform(get(API_URI).accept(MediaType.APPLICATION_JSON))
            .andDo(builder.build())
            .andExpect(status().isOk());

    }

}


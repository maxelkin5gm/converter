package com.github.maxelkin5gm.converter_backend.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


class ValuteServiceTest {

    static Document document;

    @BeforeAll
    public static void init() throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = builder.parse(ValuteServiceTest.class.getClassLoader().getResourceAsStream("valutes.xml"));
    }

    @Test
    void getValutesList() {
        var valuteServiceMock = Mockito.mock(ValuteService.class);
        Mockito.when(valuteServiceMock.buildDocument()).thenReturn(document);
        Mockito.when(valuteServiceMock.getValutesList()).thenCallRealMethod();

        assertEquals(valuteServiceMock.getValutesList().toString(),
                "[Valute(charCode=RUB, value=1.0), Valute(charCode=AUD, value=50.1132), Valute(charCode=AZN, value=44.6709)]");
    }
}
package com.github.maxelkin5gm.converter_backend.services;

import com.github.maxelkin5gm.converter_backend.entities.Valute;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class ValuteService {
    String apiUrl = "https://www.cbr.ru/scripts/XML_daily.asp";

    public List<Valute> getValutesList() {
        var document = buildDocument();
        document.getDocumentElement();
        NodeList valuteNodes = document.getDocumentElement().getElementsByTagName("Valute");
        var valuteList = new ArrayList<Valute>();
        valuteList.add(new Valute("RUB", 1));
        for (int i = 0; i < valuteNodes.getLength(); i++) {
            var valuteNode = valuteNodes.item(i).getChildNodes();
            String charCode = "";
            String value = "";
            for (int j = 0; j < valuteNode.getLength(); j++) {
                switch (valuteNode.item(j).getNodeName()) {
                    case "CharCode" -> charCode = valuteNode.item(j).getTextContent();
                    case "Value" -> value = valuteNode.item(j).getTextContent();
                }
            }
            var valute = new Valute(charCode, Float.parseFloat(value.replace(',', '.')));
            valuteList.add(valute);
        }
        return valuteList;
    }

    public Document buildDocument() {
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            builder.setErrorHandler(null);
            return builder.parse(apiUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package com.practice.mydevelopment.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.practice.mydevelopment.core.beans.SelectiveDropdownArticlesBean;
import com.practice.mydevelopment.core.beans.SelectiveDropdownEventsBean;
import com.practice.mydevelopment.core.beans.SelectiveDropdownProductsBean;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Exporters({
        @Exporter(name = "jackson",selector = "customjson",extensions = "json",
                options = {
                        @ExporterOption(name="SerializationFeature.WRAP_ROOT_VALUE", value="true"),
                        @ExporterOption(name="MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value="true")
                }),
        @Exporter(name="xmlcustomexporter",extensions = "xml",selector = "customxml")
})
@Model(adaptables = SlingHttpServletRequest.class,
        resourceType = SelectiveDropDownInfoModel.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@JsonRootName("json-exporter")
@XmlRootElement(name="xml-exporter")
public class SelectiveDropDownInfoModel {

    final protected static String RESOURCE_TYPE = "mydevelopment/components/content/selectiveDropDownInfo";
    @SlingObject
    Resource componentResource;
    @ValueMapValue
    private String id;
    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String dynamicInfo;

    @XmlElement(name="articles-xml")
    @JsonProperty(value = "articles-json")
    public List<SelectiveDropdownArticlesBean> getArticlesInformation(){
        List<SelectiveDropdownArticlesBean> articleInfo = new ArrayList<>();
        Optional<Resource> fields = Optional.ofNullable(componentResource.getChild("articleItems"));
            fields.ifPresent(resource -> {
                for(Resource child : resource.getChildren()){
                    articleInfo.add(getArticlesInfo(child));
                }
            });
        return articleInfo;
    }

    private SelectiveDropdownArticlesBean getArticlesInfo(Resource resource) {
        ValueMap vm = resource.getValueMap();

        String title = vm.get("title", String.class);
        String author = vm.get("author", String.class);
        String authorDescription = vm.get("authorDescription", String.class);
        Date publicationDate = vm.get("publicationDate", Date.class);

        return new SelectiveDropdownArticlesBean(title, author, authorDescription, publicationDate);
    }

    @XmlElement(name="events-xml")
    @JsonProperty(value = "events-json")
    public List<SelectiveDropdownEventsBean> getEventsInformation(){
        List<SelectiveDropdownEventsBean> eventInfo = new ArrayList<>();
        Optional<Resource> fields = Optional.ofNullable(componentResource.getChild("eventItems"));
        fields.ifPresent(resource -> {
            for(Resource child : resource.getChildren()){
                eventInfo.add(getEventsInfo(child));
            }
        });
        return  eventInfo;
    }
    private SelectiveDropdownEventsBean getEventsInfo(Resource resource) {
        ValueMap vm = resource.getValueMap();

        String eventName = vm.get("eventName", String.class);
        String location = vm.get("location", String.class);
        Date eventDate = vm.get("eventDate", Date.class);

        return new SelectiveDropdownEventsBean(eventName, location, eventDate);
    }

    @XmlElement(name="products-xml")
    @JsonProperty(value = "products-json")
    public List<SelectiveDropdownProductsBean> getProductsInformation(){
        List<SelectiveDropdownProductsBean> productInfo = new ArrayList<>();
        Optional<Resource> fields = Optional.ofNullable(componentResource.getChild("productItems"));
        fields.ifPresent(resource -> {
            for(Resource child : resource.getChildren()){
                productInfo.add(getProductsInfo(child));
            }
        });
        return productInfo;
    }
    private SelectiveDropdownProductsBean getProductsInfo(Resource resource) {
        ValueMap vm = resource.getValueMap();

        String productName = vm.get("productName", String.class);
        String price = vm.get("price", String.class);
        String productDescription = vm.get("productDescription", String.class);

        return new SelectiveDropdownProductsBean(productName, price, productDescription);
    }

    public String getId() {
        return id;
    }

    public String getHeading() {
        return heading;
    }

    public String getDynamicInfo() {
        return dynamicInfo;
    }
}

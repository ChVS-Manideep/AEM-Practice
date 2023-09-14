package com.practice.mydevelopment.core.models;

import com.practice.mydevelopment.core.beans.SelectiveDropdownArticlesBean;
import com.practice.mydevelopment.core.beans.SelectiveDropdownEventsBean;
import com.practice.mydevelopment.core.beans.SelectiveDropdownProductsBean;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(AemContextExtension.class)
class SelectiveDropDownInfoModelTest {

    private AemContext ctx = new AemContext();

    @BeforeEach
    void setUp() {
        ctx.addModelsForClasses(SelectiveDropDownInfoModel.class);
        ctx.load().json("/com.practice.mydevelopment.core.jsons/selective-dropdown-info.json", "/content/mydevelopment/us/en");
    }
    @Test
    void getArticlesInformation() {
        ctx = getContext();
        SelectiveDropDownInfoModel model = getModelObj(ctx);

        if (model.getArticlesInformation() != null) {
            ArrayList<SelectiveDropdownArticlesBean> list = new ArrayList<>();
            list = (ArrayList<SelectiveDropdownArticlesBean>) model.getArticlesInformation();
            assertEquals("Exploring the Benefits of Mindfulness Meditation", list.get(0).getTitle());
            assertEquals("Dr. Samantha Roberts", list.get(0).getAuthor());
            assertEquals("<p>In this thought-provoking article, Dr. Samantha Roberts explores the rapid advancements in artificial intelligence (AI) and the profound impact it is having on various industries.</p>", list.get(0).getAuthorDescription());
            assertEquals("07-06-2023", list.get(0).getPublicationDate());
        }
    }

    @Test
    void getEventsInformation() {
        ctx = getContext();
        SelectiveDropDownInfoModel model = getModelObj(ctx);

        if (model.getEventsInformation() != null) {
            ArrayList<SelectiveDropdownEventsBean> list = new ArrayList<>();
            list = (ArrayList<SelectiveDropdownEventsBean>) model.getEventsInformation();
            assertEquals("Vinayaka Chavithi", list.get(1).getEventName());
            assertEquals("Nidadavole", list.get(1).getLocation());
            assertEquals("20-08-2023", list.get(1).getEventDate());
        }
    }

    @Test
    void getProductsInformation() {
        ctx = getContext();
        SelectiveDropDownInfoModel model = getModelObj(ctx);

        if (model.getProductsInformation() != null) {
            ArrayList<SelectiveDropdownProductsBean> list = new ArrayList<>();
            list = (ArrayList<SelectiveDropdownProductsBean>) model.getProductsInformation();
            assertEquals("StellarGlo Bluetooth Speaker", list.get(0).getProductName());
            assertEquals("3980", list.get(0).getPrice());
            assertEquals("<p>Portable speaker with crisp sound and wireless connectivity.</p>\r\n", list.get(0).getProductDescription());
        }
    }

    @Test
    void getId() {
    ctx = getContext();
    SelectiveDropDownInfoModel model = getModelObj(ctx);
        assertEquals("selective_id", model.getId());
    }

    @Test
    void getHeading() {
        ctx = getContext();
        SelectiveDropDownInfoModel model = getModelObj(ctx);
        assertEquals("ARTICLES", model.getHeading());
    }

    @Test
    void getDynamicInfo() {
        ctx = getContext();
        SelectiveDropDownInfoModel model = getModelObj(ctx);
        assertEquals("events", model.getDynamicInfo());
    }
    private AemContext getContext(){
        ctx.currentResource("/content/mydevelopment/us/en/jcr:content/root/responsivegrid/selectivedropdowninf");
        return ctx;
    }
    private SelectiveDropDownInfoModel getModelObj(AemContext ctx){
        SelectiveDropDownInfoModel model = ctx.request().adaptTo(SelectiveDropDownInfoModel.class);
        return model;
    }
}
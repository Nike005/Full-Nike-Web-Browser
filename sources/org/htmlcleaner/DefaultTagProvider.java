package org.htmlcleaner;

import acr.browser.lightning.view.HomepageView;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.jdom2.JDOMConstants;

public class DefaultTagProvider implements ITagInfoProvider {
    public static final DefaultTagProvider INSTANCE = new DefaultTagProvider();
    private static final String STRONG = "strong";
    private ConcurrentMap<String, TagInfo> tagInfoMap = new ConcurrentHashMap();

    public DefaultTagProvider() {
        TagInfo tagInfo = new TagInfo("div", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("div", tagInfo);
        put("span", new TagInfo("span", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        put("meta", new TagInfo("meta", ContentType.none, BelongsTo.HEAD, false, false, false, CloseTag.forbidden, Display.none));
        put("link", new TagInfo("link", ContentType.none, BelongsTo.HEAD, false, false, false, CloseTag.forbidden, Display.none));
        put("title", new TagInfo("title", ContentType.text, BelongsTo.HEAD, false, true, false, CloseTag.required, Display.none));
        put("style", new TagInfo("style", ContentType.text, BelongsTo.HEAD, false, false, false, CloseTag.required, Display.none));
        put("bgsound", new TagInfo("bgsound", ContentType.none, BelongsTo.HEAD, false, false, false, CloseTag.forbidden, Display.none));
        TagInfo tagInfo2 = new TagInfo("h1", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo2.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo2.defineCloseBeforeTags("h1,h2,h3,h4,h5,h6,p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("h1", tagInfo2);
        TagInfo tagInfo3 = new TagInfo("h2", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo3.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo3.defineCloseBeforeTags("h1,h2,h3,h4,h5,h6,p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("h2", tagInfo3);
        TagInfo tagInfo4 = new TagInfo("h3", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo4.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo4.defineCloseBeforeTags("h1,h2,h3,h4,h5,h6,p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("h3", tagInfo4);
        TagInfo tagInfo5 = new TagInfo("h4", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo5.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo5.defineCloseBeforeTags("h1,h2,h3,h4,h5,h6,p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("h4", tagInfo5);
        TagInfo tagInfo6 = new TagInfo("h5", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo6.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo6.defineCloseBeforeTags("h1,h2,h3,h4,h5,h6,p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("h5", tagInfo6);
        TagInfo tagInfo7 = new TagInfo("h6", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo7.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo7.defineCloseBeforeTags("h1,h2,h3,h4,h5,h6,p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("h6", tagInfo7);
        TagInfo tagInfo8 = new TagInfo("p", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo8.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo8.defineCloseBeforeTags("p,p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("p", tagInfo8);
        put(STRONG, new TagInfo(STRONG, ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        put("em", new TagInfo("em", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        put("abbr", new TagInfo("abbr", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        put("acronym", new TagInfo("acronym", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        TagInfo tagInfo9 = new TagInfo("address", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo9.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo9.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("address", tagInfo9);
        put("bdo", new TagInfo("bdo", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        TagInfo tagInfo10 = new TagInfo("blockquote", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo10.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo10.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("blockquote", tagInfo10);
        put("cite", new TagInfo("cite", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        put("q", new TagInfo("q", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        put("code", new TagInfo("code", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        put("ins", new TagInfo("ins", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.any));
        put("del", new TagInfo("del", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.any));
        put("dfn", new TagInfo("dfn", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        put("kbd", new TagInfo("kbd", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        TagInfo tagInfo11 = new TagInfo("pre", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo11.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo11.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("pre", tagInfo11);
        put("samp", new TagInfo("samp", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        TagInfo tagInfo12 = new TagInfo("listing", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo12.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo12.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("listing", tagInfo12);
        put("var", new TagInfo("var", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        put("br", new TagInfo("br", ContentType.none, BelongsTo.BODY, false, false, false, CloseTag.forbidden, Display.none));
        put("wbr", new TagInfo("wbr", ContentType.none, BelongsTo.BODY, false, false, false, CloseTag.forbidden, Display.none));
        TagInfo tagInfo13 = new TagInfo("nobr", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo13.defineCloseBeforeTags("nobr");
        put("nobr", tagInfo13);
        put("xmp", new TagInfo("xmp", ContentType.text, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        TagInfo tagInfo14 = new TagInfo(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY, ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo14.defineCloseBeforeTags(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY);
        put(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY, tagInfo14);
        put("base", new TagInfo("base", ContentType.none, BelongsTo.HEAD, false, false, false, CloseTag.forbidden, Display.none));
        put("img", new TagInfo("img", ContentType.none, BelongsTo.BODY, false, false, false, CloseTag.forbidden, Display.inline));
        TagInfo tagInfo15 = new TagInfo("area", ContentType.none, BelongsTo.BODY, false, false, false, CloseTag.forbidden, Display.none);
        tagInfo15.defineFatalTags("map");
        tagInfo15.defineCloseBeforeTags("area");
        put("area", tagInfo15);
        TagInfo tagInfo16 = new TagInfo("map", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.any);
        tagInfo16.defineCloseBeforeTags("map");
        put("map", tagInfo16);
        put(HomepageView.HomePageViewPagerFragment.ARG_OBJECT, new TagInfo(HomepageView.HomePageViewPagerFragment.ARG_OBJECT, ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.any));
        TagInfo tagInfo17 = new TagInfo("param", ContentType.none, BelongsTo.BODY, false, false, false, CloseTag.forbidden, Display.none);
        tagInfo17.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo17.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("param", tagInfo17);
        put("applet", new TagInfo("applet", ContentType.all, BelongsTo.BODY, true, false, false, CloseTag.required, Display.any));
        put(JDOMConstants.NS_PREFIX_XML, new TagInfo(JDOMConstants.NS_PREFIX_XML, ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.none));
        TagInfo tagInfo18 = new TagInfo("ul", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo18.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo18.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("ul", tagInfo18);
        TagInfo tagInfo19 = new TagInfo("ol", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo19.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo19.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("ol", tagInfo19);
        TagInfo tagInfo20 = new TagInfo("li", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.optional, Display.block);
        tagInfo20.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo20.defineCloseBeforeTags("li,p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("li", tagInfo20);
        TagInfo tagInfo21 = new TagInfo("dl", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo21.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo21.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("dl", tagInfo21);
        TagInfo tagInfo22 = new TagInfo("dt", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.optional, Display.block);
        tagInfo22.defineCloseBeforeTags("dt,dd");
        put("dt", tagInfo22);
        TagInfo tagInfo23 = new TagInfo("dd", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.optional, Display.block);
        tagInfo23.defineCloseBeforeTags("dt,dd");
        put("dd", tagInfo23);
        TagInfo tagInfo24 = new TagInfo("menu", ContentType.all, BelongsTo.BODY, true, false, false, CloseTag.required, Display.block);
        tagInfo24.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo24.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("menu", tagInfo24);
        TagInfo tagInfo25 = new TagInfo("dir", ContentType.all, BelongsTo.BODY, true, false, false, CloseTag.required, Display.block);
        tagInfo25.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo25.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("dir", tagInfo25);
        TagInfo tagInfo26 = new TagInfo("table", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo26.defineAllowedChildrenTags("tr,tbody,thead,tfoot,colgroup,caption,tr");
        tagInfo26.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo26.defineCloseBeforeTags("tr,thead,tbody,tfoot,caption,colgroup,table,p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("table", tagInfo26);
        TagInfo tagInfo27 = new TagInfo("tr", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.optional, Display.block);
        tagInfo27.defineFatalTags("table");
        tagInfo27.defineRequiredEnclosingTags("tbody");
        tagInfo27.defineAllowedChildrenTags("td,th");
        tagInfo27.defineHigherLevelTags("thead,tfoot");
        tagInfo27.defineCloseBeforeTags("tr,td,th,caption,colgroup");
        put("tr", tagInfo27);
        TagInfo tagInfo28 = new TagInfo("td", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo28.defineFatalTags("table");
        tagInfo28.defineRequiredEnclosingTags("tr");
        tagInfo28.defineCloseBeforeTags("td,th,caption,colgroup");
        put("td", tagInfo28);
        TagInfo tagInfo29 = new TagInfo("th", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.optional, Display.block);
        tagInfo29.defineFatalTags("table");
        tagInfo29.defineRequiredEnclosingTags("tr");
        tagInfo29.defineCloseBeforeTags("td,th,caption,colgroup");
        put("th", tagInfo29);
        TagInfo tagInfo30 = new TagInfo("tbody", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.optional, Display.block);
        tagInfo30.defineFatalTags("table");
        tagInfo30.defineAllowedChildrenTags("tr,form");
        tagInfo30.defineCloseBeforeTags("td,th,tr,tbody,thead,tfoot,caption,colgroup");
        put("tbody", tagInfo30);
        TagInfo tagInfo31 = new TagInfo("thead", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.optional, Display.block);
        tagInfo31.defineFatalTags("table");
        tagInfo31.defineAllowedChildrenTags("tr,form");
        tagInfo31.defineCloseBeforeTags("td,th,tr,tbody,thead,tfoot,caption,colgroup");
        put("thead", tagInfo31);
        TagInfo tagInfo32 = new TagInfo("tfoot", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.optional, Display.block);
        tagInfo32.defineFatalTags("table");
        tagInfo32.defineAllowedChildrenTags("tr,form");
        tagInfo32.defineCloseBeforeTags("td,th,tr,tbody,thead,tfoot,caption,colgroup");
        put("tfoot", tagInfo32);
        TagInfo tagInfo33 = new TagInfo("col", ContentType.none, BelongsTo.BODY, false, false, false, CloseTag.forbidden, Display.block);
        tagInfo33.defineFatalTags("colgroup");
        put("col", tagInfo33);
        TagInfo tagInfo34 = new TagInfo("colgroup", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.optional, Display.block);
        tagInfo34.defineFatalTags("table");
        tagInfo34.defineAllowedChildrenTags("col");
        tagInfo34.defineCloseBeforeTags("td,th,tr,tbody,thead,tfoot,caption,colgroup");
        put("colgroup", tagInfo34);
        TagInfo tagInfo35 = new TagInfo("caption", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo35.defineFatalTags("table");
        tagInfo35.defineCloseBeforeTags("td,th,tr,tbody,thead,tfoot,caption,colgroup");
        put("caption", tagInfo35);
        TagInfo tagInfo36 = new TagInfo("form", ContentType.all, BelongsTo.BODY, false, false, true, CloseTag.required, Display.block);
        tagInfo36.defineForbiddenTags("form");
        tagInfo36.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo36.defineCloseBeforeTags("option,optgroup,textarea,select,fieldset,p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("form", tagInfo36);
        TagInfo tagInfo37 = new TagInfo("input", ContentType.none, BelongsTo.BODY, false, false, false, CloseTag.forbidden, Display.inline);
        tagInfo37.defineCloseBeforeTags("select,optgroup,option");
        put("input", tagInfo37);
        TagInfo tagInfo38 = new TagInfo("textarea", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo38.defineCloseBeforeTags("select,optgroup,option");
        put("textarea", tagInfo38);
        TagInfo tagInfo39 = new TagInfo("select", ContentType.all, BelongsTo.BODY, false, false, true, CloseTag.required, Display.inline);
        tagInfo39.defineAllowedChildrenTags("option,optgroup");
        tagInfo39.defineCloseBeforeTags("option,optgroup,select");
        put("select", tagInfo39);
        TagInfo tagInfo40 = new TagInfo("option", ContentType.text, BelongsTo.BODY, false, false, true, CloseTag.optional, Display.inline);
        tagInfo40.defineFatalTags("select");
        tagInfo40.defineCloseBeforeTags("option");
        put("option", tagInfo40);
        TagInfo tagInfo41 = new TagInfo("optgroup", ContentType.all, BelongsTo.BODY, false, false, true, CloseTag.required, Display.inline);
        tagInfo41.defineFatalTags("select");
        tagInfo41.defineAllowedChildrenTags("option");
        tagInfo41.defineCloseBeforeTags("optgroup");
        put("optgroup", tagInfo41);
        TagInfo tagInfo42 = new TagInfo("button", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.any);
        tagInfo42.defineCloseBeforeTags("select,optgroup,option");
        put("button", tagInfo42);
        put("label", new TagInfo("label", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline));
        TagInfo tagInfo43 = new TagInfo("fieldset", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo43.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo43.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("fieldset", tagInfo43);
        TagInfo tagInfo44 = new TagInfo("isindex", ContentType.none, BelongsTo.BODY, true, false, false, CloseTag.forbidden, Display.block);
        tagInfo44.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo44.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("isindex", tagInfo44);
        put("script", new TagInfo("script", ContentType.all, BelongsTo.HEAD_AND_BODY, false, false, false, CloseTag.required, Display.none));
        put("noscript", new TagInfo("noscript", ContentType.all, BelongsTo.HEAD_AND_BODY, false, false, false, CloseTag.required, Display.block));
        TagInfo tagInfo45 = new TagInfo("b", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo45.defineCloseInsideCopyAfterTags("u,i,tt,sub,sup,big,small,strike,blink,s");
        put("b", tagInfo45);
        TagInfo tagInfo46 = new TagInfo("i", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo46.defineCloseInsideCopyAfterTags("b,u,tt,sub,sup,big,small,strike,blink,s");
        put("i", tagInfo46);
        TagInfo tagInfo47 = new TagInfo("u", ContentType.all, BelongsTo.BODY, true, false, false, CloseTag.required, Display.inline);
        tagInfo47.defineCloseInsideCopyAfterTags("b,i,tt,sub,sup,big,small,strike,blink,s");
        put("u", tagInfo47);
        TagInfo tagInfo48 = new TagInfo("tt", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo48.defineCloseInsideCopyAfterTags("b,u,i,sub,sup,big,small,strike,blink,s");
        put("tt", tagInfo48);
        TagInfo tagInfo49 = new TagInfo("sub", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo49.defineCloseInsideCopyAfterTags("b,u,i,tt,sup,big,small,strike,blink,s");
        put("sub", tagInfo49);
        TagInfo tagInfo50 = new TagInfo("sup", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo50.defineCloseInsideCopyAfterTags("b,u,i,tt,sub,big,small,strike,blink,s");
        put("sup", tagInfo50);
        TagInfo tagInfo51 = new TagInfo("big", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo51.defineCloseInsideCopyAfterTags("b,u,i,tt,sub,sup,small,strike,blink,s");
        put("big", tagInfo51);
        TagInfo tagInfo52 = new TagInfo("small", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo52.defineCloseInsideCopyAfterTags("b,u,i,tt,sub,sup,big,strike,blink,s");
        put("small", tagInfo52);
        TagInfo tagInfo53 = new TagInfo("strike", ContentType.all, BelongsTo.BODY, true, false, false, CloseTag.required, Display.inline);
        tagInfo53.defineCloseInsideCopyAfterTags("b,u,i,tt,sub,sup,big,small,blink,s");
        put("strike", tagInfo53);
        TagInfo tagInfo54 = new TagInfo("blink", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.inline);
        tagInfo54.defineCloseInsideCopyAfterTags("b,u,i,tt,sub,sup,big,small,strike,s");
        put("blink", tagInfo54);
        TagInfo tagInfo55 = new TagInfo("marquee", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.block);
        tagInfo55.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo55.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("marquee", tagInfo55);
        TagInfo tagInfo56 = new TagInfo("s", ContentType.all, BelongsTo.BODY, true, false, false, CloseTag.required, Display.inline);
        tagInfo56.defineCloseInsideCopyAfterTags("b,u,i,tt,sub,sup,big,small,strike,blink");
        put("s", tagInfo56);
        TagInfo tagInfo57 = new TagInfo("hr", ContentType.none, BelongsTo.BODY, false, false, false, CloseTag.forbidden, Display.block);
        tagInfo57.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo57.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("hr", tagInfo57);
        put("font", new TagInfo("font", ContentType.all, BelongsTo.BODY, true, false, false, CloseTag.required, Display.inline));
        put("basefont", new TagInfo("basefont", ContentType.none, BelongsTo.BODY, true, false, false, CloseTag.forbidden, Display.none));
        TagInfo tagInfo58 = new TagInfo("center", ContentType.all, BelongsTo.BODY, true, false, false, CloseTag.required, Display.block);
        tagInfo58.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo58.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("center", tagInfo58);
        put("comment", new TagInfo("comment", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.none));
        put("server", new TagInfo("server", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.none));
        put("iframe", new TagInfo("iframe", ContentType.all, BelongsTo.BODY, false, false, false, CloseTag.required, Display.any));
        TagInfo tagInfo59 = new TagInfo("embed", ContentType.none, BelongsTo.BODY, false, false, false, CloseTag.forbidden, Display.block);
        tagInfo59.defineCloseBeforeCopyInsideTags("a,bdostrongem,q,b,i,u,tt,sub,sup,big,small,strike,s,font");
        tagInfo59.defineCloseBeforeTags("p,address,label,abbr,acronym,dfn,kbd,samp,var,cite,code,param,xml");
        put("embed", tagInfo59);
    }

    private void put(String str, TagInfo tagInfo) {
        this.tagInfoMap.put(str, tagInfo);
    }

    public TagInfo getTagInfo(String str) {
        if (str == null) {
            return null;
        }
        return (TagInfo) this.tagInfoMap.get(str);
    }
}
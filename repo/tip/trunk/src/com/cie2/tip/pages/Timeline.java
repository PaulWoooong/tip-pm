package com.cie2.tip.pages;

import com.cie2.tip.components.base.CieUserPage;


//@IncludeJavaScriptLibrary("timeline-api.js")
public class Timeline extends CieUserPage{

//    @Parameter(value = "Are you sure?", defaultPrefix = BindingConstants.LITERAL)
//    private String message;

//    @Inject
//    private RenderSupport renderSupport;
//
//    @InjectContainer
//    private ClientElement element;
//
//    @AfterRender
//    public void afterRender() {
//            renderSupport.addScript(String.format("new Confirm('%s', '%s');",
//                    element.getClientId(), message));

//
//
//    
//    	StringBuffer sb = new StringBuffer(256);
//    	
//        sb.append("var tl;");
//        sb.append("	function onLoad() {");
//        sb.append("		var bandInfos = [");
//        sb.append("Timeline.createBandInfo({");
//        sb.append("width:          \"70%\",");
//        sb.append("intervalUnit:   Timeline.DateTime.MONTH,");
//        sb.append("intervalPixels: 100");
//        sb.append("}),");
//        sb.append("Timeline.createBandInfo({");
//        sb.append("width:          \"30%\",");
//        sb.append("intervalUnit:   Timeline.DateTime.YEAR,");
//        sb.append("intervalPixels: 200");
//        sb.append("})");
//        sb.append("];");
//        sb.append("bandInfos[1].syncWith = 0;");
//        sb.append("bandInfos[1].highlight = true;");
//        sb.append("tl = Timeline.create(document.getElementById(\"my-timeline\"), bandInfos);");
//        sb.append("}");
//        sb.append("var resizeTimerID = null;");
//        sb.append("function onResize() {");
//        sb.append("if (resizeTimerID == null) {");
//        sb.append("resizeTimerID = window.setTimeout(function() {");
//        sb.append("resizeTimerID = null;");
//        sb.append("tl.layout();");
//        sb.append("}, 500);");
//        sb.append("}");
//        sb.append("} ");
//        
//    	renderSupport.addScript(sb.toString());        
//    }
}

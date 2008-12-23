package com.cie2.tip.components;

import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.TypeCoercer;
import org.apache.tapestry5.services.Response;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;

public class Chart{
    
    /**list(array) of paired values(label & value): [String,Number,String,Number,...]*/
    @Parameter(required=true)
    private List<Object> _context;

    @Parameter(required=true)
    private int _width;

    @Parameter(required=true)
    private int _height;

    /** width and height of the popup chart, if omitted, javascript for popup chart is omitted from output*/
    @Parameter
    private int[] _popup;
    
    @Inject
    private ComponentResources _resources;

    @Inject
    private TypeCoercer _coercer;
    
    @SuppressWarnings("unchecked")
    void beginRender(MarkupWriter writer)
    {
        //add width and height to begining of parameters
        Object[] contextArray = _context == null ? new Object[0] : _context.toArray();
        Object[] params = new Object[contextArray.length+2];
        System.arraycopy(contextArray, 0, params, 2, contextArray.length);
        params[0] = new Integer(_width);
        params[1] = new Integer(_height);
        
        //generate action link
        Link link = _resources.createActionLink("chart", false, params);
        Element img = writer.element("img", "src", link);
        
        //add javascript for popup
        if(_popup != null && _popup.length > 1){
            params[0] = _popup[0];
            params[1] = _popup[1];
            link = _resources.createActionLink("chart", false, params);
            img.attribute("onclick", "window.open('"+link+"','_blank','width="+(_popup[0]+24)+", height="+(_popup[1]+24)+"')");
            img.attribute("style", "cursor:pointer");
        }

        _resources.renderInformalParameters(writer);
    }

    void afterRender(MarkupWriter writer)
    {
        writer.end();
    }

    public StreamResponse onMyChart(final int width, final int height, Object ...rest){
        DefaultKeyedValues values = new DefaultKeyedValues();
        for (int i = 3; i < rest.length; i+=2){
            values.addValue(rest[i-1].toString(), _coercer.coerce(rest[i], Number.class));
        }        
        PieDataset pieDataset = new DefaultPieDataset(values);

        PiePlot3D plot = new PiePlot3D(pieDataset);
        plot.setForegroundAlpha(0.5f);
        plot.setDepthFactor(0.1);
        plot.setCircular(true);
        
        final JFreeChart chart = new JFreeChart(plot);
        
        return new StreamResponse(){
            public String getContentType(){
                return "image/jpeg";
            }
            public InputStream getStream() throws IOException {
                BufferedImage image  = chart.createBufferedImage(width, height);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream() ;
                ChartUtilities.writeBufferedImageAsPNG(byteArray, image) ;
                return new ByteArrayInputStream(byteArray.toByteArray());
            }
            public void prepareResponse(Response response){}
        };
    }
    
    public StreamResponse onChart(final int width, final int height, Object ...rest) {

        final TimeSeries s1 = new TimeSeries("Random Data", Week.class);
        RegularTimePeriod t = new Week();
        double v = 100.0;
        for (int i = 0; i < 10; i++) {
            s1.add(t, v);
            v = v * (1 + ((Math.random() - 0.499) / 100.0));
            t = t.next();
        }
        
        final TimeSeries s2 = new TimeSeries("L&G UK Index Trust", Month.class);
        s2.add(new Month(2, 2008), 129.6);
        s2.add(new Month(3, 2008), 123.2);
        s2.add(new Month(4, 2008), 117.2);
 
        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        dataset.setDomainIsPointsInTime(true);    	
    	
    	 final JFreeChart chart = ChartFactory.createTimeSeriesChart(
    	            "Legal & General Unit Trust Prices",
    	            "Date", "Price Per Unit",
    	            dataset,
    	            true,
    	            true,
    	            false
    	        );
    	 
    	 
        return new StreamResponse(){
            public String getContentType(){
                return "image/png";
            }
            public InputStream getStream() throws IOException {
                BufferedImage image  = chart.createBufferedImage(width, height);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream() ;
                ChartUtilities.writeBufferedImageAsPNG(byteArray, image) ;
                return new ByteArrayInputStream(byteArray.toByteArray());
            }
            public void prepareResponse(Response response){}
        };
    	
    }
}

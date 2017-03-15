package de.lespace.webrtclibs.jwebrtc2;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */



import org.kurento.client.Composite;
import org.kurento.client.HubPort;
import org.kurento.client.KurentoClient;
import org.kurento.client.MediaPipeline;
import org.kurento.client.RecorderEndpoint;
import org.kurento.client.WebRtcEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kurento.client.FaceOverlayFilter;
import org.kurento.module.instacut.instaCut;
import org.kurento.module.saveimagefilter.saveImageFilter;
import org.kurento.module.imagecomposite.imageComposite;

/**
 * Media Pipeline (WebRTC endpoints, i.e. Kurento Media Elements) and
 * connections for the 1 to 1 video communication.
 * 
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @author Micael Gallego (micael.gallego@gmail.com)
 * @since 4.3.1
 */
public class CallMediaPipeline {
	private static final Logger log = LoggerFactory.getLogger(CallMediaPipeline.class);
	
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-S");

	// TODO define as environment variables
	public static final String RECORDING_DIR = "file:///var/kurento/";
	
//	public static final String RECORDING_PATH = RECORDING_DIR + df.format(new Date()) + "-";
	public static final String RECORDING_EXT = ".webm";

	private MediaPipeline pipeline;
	private WebRtcEndpoint callerWebRtcEp;
	private WebRtcEndpoint calleeWebRtcEp;
	private RecorderEndpoint calleeRecorder;
	private RecorderEndpoint callerRecorder;
	private instaCut ic;
	public instaCut ic2;

	public CallMediaPipeline(KurentoClient kurento, String from, String to) {
		String date = df.format(new Date());
		
		try {
			this.pipeline = kurento.createMediaPipeline();
			this.callerWebRtcEp = new WebRtcEndpoint.Builder(pipeline).build();
			this.calleeWebRtcEp = new WebRtcEndpoint.Builder(pipeline).build();
			
			this.callerRecorder = new RecorderEndpoint.Builder(pipeline, RECORDING_DIR + date + "-" + from + RECORDING_EXT).build();
			this.calleeRecorder = new RecorderEndpoint.Builder(pipeline, RECORDING_DIR + date + "-" + to + RECORDING_EXT).build();

			this.callerWebRtcEp.connect(this.calleeWebRtcEp);
			this.callerWebRtcEp.connect(this.callerRecorder);
			
			this.calleeWebRtcEp.connect(this.callerWebRtcEp);
			this.calleeWebRtcEp.connect(this.calleeRecorder);
			
			/* Composite composite = new Composite.Builder(pipeline).build();

            HubPort portIn1  = new HubPort.Builder(composite).build();
            HubPort portIn2  = new HubPort.Builder(composite).build();
            HubPort portOut1 = new HubPort.Builder(composite).build();

           
            this.callerWebRtcEp.connect(portIn1);
            this.calleeWebRtcEp.connect(portIn2);
           
	    ic=new instaCut.Builder(pipeline).build();
		portOut1.connect(ic);
		ic.connect(calleeWebRtcEp);	
		ic.connect(callerWebRtcEp); */
           
 
       
       /* saveImageFilter sf=new saveImageFilter.Builder(pipeline).build();
        callerWebRtcEp.connect(sf);
        sf.connect(callerWebRtcEp);
        sf.connect(calleeWebRtcEp);
        sf.saveImage("/b.png");*/
        
        /*imageComposite imc=new imageComposite.Builder(pipeline).build();
        calleeWebRtcEp.connect(imc);
        imc.connect(calleeWebRtcEp);
        imc.connect(callerWebRtcEp);
        imc.readImage("/b.png");*/
			  
		} catch (Throwable t) {
			if (this.pipeline != null) {
				pipeline.release();
			}
			log.error("Unable to create instance of CallMediaPipeline!", t.getMessage());
		}
	}

	public String generateSdpAnswerForCaller(String sdpOffer) {
		return callerWebRtcEp.processOffer(sdpOffer);
	}

	public String generateSdpAnswerForCallee(String sdpOffer) {
		return calleeWebRtcEp.processOffer(sdpOffer);
	}

	public void release() {
		if (pipeline != null) {
			pipeline.release();
		}
	}

	public WebRtcEndpoint getCallerWebRtcEp() {
		return callerWebRtcEp;
	}

	public WebRtcEndpoint getCalleeWebRtcEp() {
		return calleeWebRtcEp;
	}

	public void record() {
		log.debug("Start recording...");
		calleeRecorder.record();
		callerRecorder.record();
	}

	public MediaPipeline getPipeline() {
		return pipeline;
	}
	
	public void save(){
		
	}

}

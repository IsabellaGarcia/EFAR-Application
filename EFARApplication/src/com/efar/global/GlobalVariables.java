/**
 * 
 */
package com.efar.global;

import java.util.Vector;

import android.app.Application;
import com.efar.datamodel.*;

/**
 * @author 		Michyo
 * @function 	GlobalVariables extends from Application Class.
 * 			 	This enables all variables in it can be invoke
 * 			 	in every where when the application is running.
 */

public class GlobalVariables extends Application {
	
	private Vector<EventModel> events;
	private EventModel eventNow;

	/**
	 * @return the event_under_processing
	 */
	public Vector<EventModel> getEvents() {
		return events;
	}

	/**
	 * @param event_under_processing the event_under_processing to set
	 */
	public void setEvents(Vector<EventModel> events) {
		this.events = events;
	}
	
	/**
	 * Add one event into events.
	 * @param e
	 */
	public void addEvent(EventModel e) {
		events.add(e);
	}

	/**
	 * Get the event at number of id.
	 * @param id
	 * @return events[id] / EventModel
	 */
	public EventModel getEventById(int id) {
		return events.get(id);
	}

	/**
	 * @return the eventNow
	 */
	public EventModel getEventNow() {
		return eventNow;
	}

	/**
	 * @param eventNow the eventNow to set
	 */
	public void setEventNow(EventModel eventNow) {
		this.eventNow = eventNow;
	}

}

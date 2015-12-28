package com.facebook.graph.user;

public enum EventType {

	ATTENDING ("attending"),
	MAYBE ("maybe"),
	DECLINED ("declined"), 
	NOT_REPLIED ("not_replied");
	
	private final String value;
	
	EventType( String v ) {
		value = v;
	}
	
    public static EventType fromValue(String v) {
        for (EventType c: EventType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }
    
    public String toString() { return value; }
}

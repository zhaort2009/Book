package com.refer.test;

import java.util.HashMap;

public class Entry {

	/**
	 * @param args
	 */
	private int key;
	private double value;
	private Entry nextentryReferenc;
	
	public Entry(int key, double value, Entry nextentryReferenc) {
		super();
		this.key = key;
		this.value = value;
		this.nextentryReferenc = nextentryReferenc;
	}

	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Entry getNextentryReferenc() {
		return nextentryReferenc;
	}

	public void setNextentryReferenc(Entry nextentryReferenc) {
		this.nextentryReferenc = nextentryReferenc;
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, Entry> a=new HashMap<Integer, Entry>();
		Entry e2=new Entry(2, 2.5, null);
		Entry e1=new Entry(1, 1.5, e2);
		a.put(e1.getKey(), e1);
		a.put(e2.getKey(), e2);
        Entry e=a.get(1);
        a.remove(e.getNextentryReferenc().getKey());
        System.out.println(a.size());
        
        System.out.println(e1.getKey());
        
		
	}

}

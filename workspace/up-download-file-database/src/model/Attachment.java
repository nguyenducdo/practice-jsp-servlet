package model;

import java.sql.Blob;

public class Attachment {
	private long id;
	private String file_name;
	private Blob file_data;
	private String description;
	
	public Attachment() {
		
	}
	
	
	
	public Attachment(long id, String file_name, Blob file_data, String description) {
		super();
		this.id = id;
		this.file_name = file_name;
		this.file_data = file_data;
		this.description = description;
	}



	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the file_name
	 */
	public String getFile_name() {
		return file_name;
	}
	/**
	 * @param file_name the file_name to set
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	/**
	 * @return the file_data
	 */
	public Blob getFile_data() {
		return file_data;
	}
	/**
	 * @param file_data the file_data to set
	 */
	public void setFile_data(Blob file_data) {
		this.file_data = file_data;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

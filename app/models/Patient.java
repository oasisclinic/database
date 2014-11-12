package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import play.data.validation.Constraints.*;
import uk.co.panaxiom.playjongo.PlayJongo;

import java.util.*;

@ApiModel(value = "A patient is a client of the clinic")
public class Patient {

    @JsonIgnore
    private static final MongoCollection collection = PlayJongo.getCollection("patients");

    @JsonProperty("_id")
    private ObjectId _id;

    @ApiModelProperty(value = "a unique identifier for a patient", required = false)
    private String patientId;

    @Required(message = "You must provide the medical ID of the patient")
    @ApiModelProperty(value = "the UNC medical ID of the patient", required = true)
    private String medicalId;

    @Required(message = "You must provide the first name of the patient")
    @ApiModelProperty(value = "the legal given name of the patient", required = true)
    private String firstName;

    @Required(message = "You must provide the last name of the patient")
    @ApiModelProperty(value = "the legal surname of the patient", required = true)
    private String lastName;

    @ApiModelProperty(value = "the date of the last interaction regarding the patient", required = false)
    private Date lastInteraction;

    public Patient save() {
        collection.save(this);
        return this;
    }

    public static List<Patient> findMostRecent(Integer limit) {
        return new MongoList<Patient>(collection.find().limit(limit).sort("{lastInteraction: -1}"), Patient.class).getList();
    }

    public static List<Patient> findAll() {
        return new MongoList<Patient>(collection.find(), Patient.class).getList();
    }

    public static Patient findOne(String patientId) {
        return collection.findOne("{patientId: #}", patientId).as(Patient.class);
    }

    public String getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(String medicalId) {
        this.medicalId = medicalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId.toString();
    }

    public Date getLastInteraction() {
        return lastInteraction;
    }

    public void setLastInteraction(Date lastInteraction) {
        this.lastInteraction = lastInteraction;
    }
}
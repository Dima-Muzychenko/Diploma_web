package entity;

import org.locationtech.jts.geom.Point;
//import org.postgis.Geometry;

import javax.persistence.*;

@Entity
public class sto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "info_id")
    private Integer infoId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "owner")
    private String owner;
    @Basic
    @Column(name = "quality")
    private Double quality;
    @Basic
    @Column(name = "speed")
    private Double speed;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "service_range")
    private Double serviceRange;
    @Basic
    @Column(name = "evaluation")
    private Double evaluation;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "lat")
    private Double lat;
    @Basic
    @Column(name = "lon")
    private Double lon;
    @Basic
    @Column(name = "result_value")
    private String resultValue;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "comments")
    private String comments;
    @Basic
    @Column(name = "working")
    private String working;

    @Basic
    @Column(name = "geo", columnDefinition = "geometry(Point, 4326)", nullable = false)
    private Point geo;

    public sto(){
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getQuality() {
        return quality;
    }

    public void setQuality(Double quality) {
        this.quality = quality;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getServiceRange() {
        return serviceRange;
    }

    public void setServiceRange(Double serviceRange) {
        this.serviceRange = serviceRange;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public Point getGeo() {
        return geo;
    }

    public void setGeo(Point location) {
        this.geo = location;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getPassword() {
        return password;
    }

    public String getComments() {
        return comments;
    }

    public String getWorking() {
        return working;
    }

}

package entity;

import jakarta.persistence.*;
//import org.postgis.Point;
////import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "sto")
public class Sto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "info_id")
    private int infoId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "owner")
    private String owner;
    @Basic
    @Column(name = "quality")
    private Integer quality;
    @Basic
    @Column(name = "speed")
    private Integer speed;
    @Basic
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "service_range")
    private Integer serviceRange;
    @Basic
    @Column(name = "evaluation")
    private Double evaluation;
    @Basic
    @Column(name = "address")
    private String address;
//    @Basic
//    @Column(name = "location", columnDefinition = "geometry(Point,4326)")
//    private Point location;
    @Basic
    @Column(name = "lat")
    private Double lat;
    @Basic
    @Column(name = "lon")
    private Double lon;
    @Basic
    @Column(name = "result_value")
    private String resultValue;

//    public Sto(int infoId, String name, String owner, Integer quality, Integer speed, Integer price, Integer serviceRange, Double evaluation, String address, Point location, Double lat, Double lon, String resultValue) {
//        this.infoId = infoId;
//        this.name = name;
//        this.owner = owner;
//        this.quality = quality;
//        this.speed = speed;
//        this.price = price;
//        this.serviceRange = serviceRange;
//        this.evaluation = evaluation;
//        this.address = address;
//        this.location = location;
//        this.lat = lat;
//        this.lon = lon;
//        this.resultValue = resultValue;
//    }

    public Sto(){

    }
    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
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

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getServiceRange() {
        return serviceRange;
    }

    public void setServiceRange(Integer serviceRange) {
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

//    public Point getGoepossition() {
//        return location;
//    }
//
//    public void setGoepossition(Point goepossition) {
//        this.location = goepossition;
//    }

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
}

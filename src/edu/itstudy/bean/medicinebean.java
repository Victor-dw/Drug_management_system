package edu.itstudy.bean;

public class medicinebean {
    private int medicineId;
    private String medicineName;
    private String medicineIntroduce;
    private int isOTC;
    private String dosage;
    private String img;


    public medicinebean(int medicineId, String medicineName, String medicineIntroduce, int isOTC, String dosage , String img) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineIntroduce = medicineIntroduce;
        this.isOTC = isOTC;
        this.dosage = dosage;
        this.img = img;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineIntroduce() {
        return medicineIntroduce;
    }

    public void setMedicineIntroduce(String medicineIntroduce) {
        this.medicineIntroduce = medicineIntroduce;
    }

    public int getIsOTC() {
        return isOTC;
    }

    public void setIsOTC(int isOTC) {
        this.isOTC = isOTC;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}
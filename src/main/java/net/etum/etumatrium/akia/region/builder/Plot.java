package net.etum.etumatrium.akia.region.builder;

import java.util.ArrayList;
import java.util.List;

public class Plot extends Region {

    // Enum pour le type de transaction
    public enum TransactionType {
        UNKNOWN,
        BUY,
        RENT
    }

    // Champs spécifiques aux plots
    private String plotName;
    private String owner;
    private final List<String> guests;
    private double price;
    private TransactionType transactionType;
    private boolean isFree;

    // Constructeur
    public Plot(int id, List<Coordinate> coordinateList, String name, boolean showTitle, Member memberManager, Group groupManager) {
        super(id, coordinateList, name, showTitle, memberManager, groupManager);
        this.guests = new ArrayList<>();
    }

    // Méthodes pour gérer les invités
    public void addGuest(String guest) {
        guests.add(guest);
    }

    public void removeGuest(String guest) {
        guests.remove(guest);
    }

    public List<String> getGuests() {
        return new ArrayList<>(guests);
    }

    // Méthodes pour gérer le statut du plot
    public void setOwner(String owner) {
        this.owner = owner;
        this.isFree = false;
    }

    public void clearOwner() {
        this.owner = null;
        this.isFree = true;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    // Autres méthodes utiles
    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Plot{" +
                "plotName='" + plotName + '\'' +
                ", owner='" + owner + '\'' +
                ", guests=" + guests +
                ", price=" + price +
                ", transactionType=" + transactionType +
                ", isFree=" + isFree +
                "} " + super.toString();
    }
}

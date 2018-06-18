package nl.rabo.statement.record;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Objects;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Record implements Serializable {
    private Integer reference;
    private String description;
    private String accountNumber;
    private String startBalance;
    private String mutation;
    private String endBalance;

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(String startBalance) {
        this.startBalance = startBalance;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public String getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(String endBalance) {
        this.endBalance = endBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(reference, record.reference) &&
                Objects.equals(description, record.description) &&
                Objects.equals(accountNumber, record.accountNumber) &&
                Objects.equals(startBalance, record.startBalance) &&
                Objects.equals(mutation, record.mutation) &&
                Objects.equals(endBalance, record.endBalance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reference, description, accountNumber, startBalance, mutation, endBalance);
    }
}

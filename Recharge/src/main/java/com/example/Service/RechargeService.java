package com.example.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Exception.RechargeNotFoundException;
import com.example.Model.Recharge;

@Service
public class RechargeService {

    private final ArrayList<Recharge> recharges = new ArrayList<>();
    private long nextId = 1;

    private Recharge copyRecharge(Recharge recharge) {
        return new Recharge(
            recharge.getRechargeId(),
            recharge.getMobileNumber(),
            recharge.getOperator(),
            recharge.getAmount(),
            recharge.getPlanType()
        );
    }

    // Add recharge
    public Recharge addRecharge(Recharge request) {

        Recharge recharge = new Recharge(
            nextId++,
            request.getMobileNumber(),
            request.getOperator().trim(),
            request.getAmount(),
            request.getPlanType().trim()
        );

        recharges.add(recharge);
        return copyRecharge(recharge);
    }

    // Get all recharges
    public List<Recharge> getAllRecharges() {
        return recharges.stream()
                .map(this::copyRecharge)
                .toList();
    }

    // Search recharge by ID
    public Recharge getRechargeById(Long id) {

        Recharge recharge = recharges.stream()
                .filter(item -> item.getRechargeId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new RechargeNotFoundException(
                        "Recharge not found with ID: " + id
                    ));

        return copyRecharge(recharge);
    }

    // Update recharge
    public Recharge updateRecharge(Long id, Recharge request) {

        Recharge existingRecharge = recharges.stream()
                .filter(item -> item.getRechargeId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new RechargeNotFoundException(
                        "Recharge not found with ID: " + id
                    ));

        existingRecharge.setMobileNumber(request.getMobileNumber());
        existingRecharge.setOperator(request.getOperator().trim());
        existingRecharge.setAmount(request.getAmount());
        existingRecharge.setPlanType(request.getPlanType().trim());

        return copyRecharge(existingRecharge);
    }

    // Delete recharge
    public void deleteRecharge(Long id) {

        Recharge recharge = recharges.stream()
                .filter(item -> item.getRechargeId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new RechargeNotFoundException(
                        "Recharge not found with ID: " + id
                    ));

        recharges.remove(recharge);
    }

    // Count recharges
    public int getRechargeCount() {
        return recharges.size();
    }
}
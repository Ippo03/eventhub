package com.example.eventhub.memorydao;

import com.example.eventhub.dao.PurchaseDAO;
import com.example.eventhub.domain.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDAOMemory implements PurchaseDAO {
    protected static List<Purchase> purchases = new ArrayList<Purchase>();

    @Override
    public void delete(Purchase purchase) {
        purchases.remove(purchase);
    }

    @Override
    public List<Purchase> findAll() {
        ArrayList<Purchase> result = new ArrayList<Purchase>();
        result.addAll(purchases);
        return result;
    }

    @Override
    public void save(Purchase purchase) {
        purchases.add(purchase);
    }

    @Override
    public Purchase find(int id) {
        for(Purchase purchase : purchases) {
            if (purchase.getPurchaseId() == id) {
                return purchase;
            }
        }
        return null;
    }

    @Override
    public int nextId() {
        return purchases.size() == 0 ? 1 : purchases.get(purchases.size() - 1).getPurchaseId() + 1;
    }
}

package com.veterinary.billing;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.veterinary.Owner;
import com.veterinary.Service;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

public class LineItemsBill extends MultipleOwnerBill {
    private final List<LineItem> lineItems;

    public LineItemsBill(Bill bill, LineItem... lineItems) {
        super(bill, collectOwners(lineItems));
        this.lineItems = newArrayList(lineItems);
    }

    @Override
    protected int totalCostFor(final Owner owner) {
        int billValue = 0;

        List<Service> services = FluentIterable.from(lineItems).filter(new Predicate<LineItem>() {
            @Override
            public boolean apply(LineItem lineItem) {
                return lineItem.owner() == owner;
            }
        }).transformAndConcat(new Function<LineItem, Iterable<Service>>() {
            @Override
            public Iterable<Service> apply(LineItem lineItem) {
                return lineItem.services;
            }
        }).toList();

        for (Service service : services) {
            if(bill.hasService(service)){
                billValue += bill.costOf(service);
            }
        }

        return billValue;
    }

    private static Owner[] collectOwners(LineItem[] lineItems) {
        return Collections2.transform(newArrayList(lineItems), new Function<LineItem, Owner>() {
            @Override
            public Owner apply(LineItem lineItem) {
                return lineItem.owner();
            }
        }).toArray(new Owner[0]);
    }

    public static class LineItem {
        private Owner owner;
        private Set<Service> services;

        private LineItem(Owner owner, Service... services) {
            this.owner = owner;
            this.services = newHashSet(services);
        }

        private Owner owner() {
            return this.owner;
        }

        public static LineItem newLineItem(Owner owner, Service... services) {
            return new LineItem(owner, services);
        }
    }
}

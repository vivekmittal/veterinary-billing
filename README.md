veterinary-billing
==================

[![Build Status](https://travis-ci.org/vivekmittal/veterinary-billing.svg?branch=master)](https://travis-ci.org/vivekmittal/veterinary-billing)
[![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/vivekmittal/veterinary-billing/blob/master/LICENSE)

<B><i>A demonstration of Fluent interfaces / TDD / Functional idioms applied with OOPS</i></b>

This application handle modes of billing in a veterinary hospital that can be done in three ways:

<B>Single Owner</B>
- One person pays all the bill

<B>Multiple Owners</B>
- Bill distibuted evenly
- One Pays it all
- Each Owner is responsible for some services and pays for the same

<b>Billing Mappings (Cost of services for various animals) are declared in class com.veterinary.Animal</b>

USAGE
-------

<b>Meta Data</b>
<pre>
private static final Owner BOB = new Owner("BOB");
private static final Owner JOHN = new Owner("JOHN");
</pre>

<b>Single Owner</b>

<pre>
Patient patient = new Patient(Animal.CAT, "Kessels");

patient.perform(Procedure.REGULAR_CHECKUP);

SingleOwnerBill decoratedBill = new SingleOwnerBill(patient.bill(), BOB);

System.out.println(decoratedBill);
</pre>

<b>Multiple Owner - Bill Distributed Evenly</b>

<pre>
Patient patient = new Patient(Animal.HORSE, "Blacky");

patient.perform(Procedure.REGULAR_CHECKUP);
patient.perform(Procedure.SURGERY);

MultipleOwnerBill decoratedBill = new DistributeEvenlyBill(patient.bill(), BOB, JOHN);

System.out.println(decoratedBill.costOccurredFor(BOB));
System.out.println(decoratedBill.costOccurredFor(JOHN));
System.out.println(decoratedBill);
</pre>

<b>Multiple Owner - One owner pays it all</b>

<pre>
Patient patient = new Patient(Animal.HORSE, "Blacky");

patient.perform(Procedure.REGULAR_CHECKUP);
patient.perform(Procedure.SURGERY);

MultipleOwnerBill decoratedBill = new OneOwnerPaysItAll(patient.bill(), BOB, BOB, JOHN);

System.out.println(decoratedBill.costOccurredFor(BOB));
System.out.println(decoratedBill.costOccurredFor(JOHN));
System.out.println(decoratedBill);
</pre>

<b>Multiple Owner - Every owner is responsible for some line items (Procedures/Medicines)</b>

<pre>
Patient patient = new Patient(Animal.HORSE, "Whitey");

patient.perform(Procedure.REGULAR_CHECKUP);
patient.perform(Medicine.PARACETAMOL);
patient.perform(Procedure.SURGERY);

MultipleOwnerBill decoratedBill = new LineItemsBill(patient.bill(),
                newLineItem(BOB, Procedure.SURGERY, Medicine.PARACETAMOL),
                newLineItem(JOHN, Procedure.REGULAR_CHECKUP));

System.out.println(decoratedBill.costOccurredFor(BOB));
System.out.println(decoratedBill.costOccurredFor(JOHN));
System.out.println(decoratedBill);
</pre>

# Auction system

## Introduction

Specification of functional requirements as part of computerisation of the product sale process based on the auction mechanism.

## Business processes

---
<a id="bc1"></a>
### BC1: Auction sale

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Description:** Business process describing a sale by the auction mechanism.

**Main scenario:**
1. [Seller](#ac1) offers the product at an auction. ([UC1](#uc1))
2. [Buyer](#ac2) offers a bid for the product that is higher than the currently highest bid. ([BR1](#br1))
3. [Buyer](#ac2) wins the auction ([BR2](#br2))
4. [Buyer](#ac2) transfers the amount due to the Seller.
5. [Seller](#ac1) transfers the product to the Buyer.

**Alternative scenarios:** 

2.A. Buyer's bid has been outbid and [Buyer](#ac2) wants to outbid the current highest bid.
* 2.A.1. Continue at step 2.

3.A. Auction time has elapsed and [Buyer](#ac2) has lost the auction. ([BR2](#br2))
* 3.A.1. End of use case.

---

## Actors

<a id="ac1"></a>
### AC1: Seller

A person offering goods at an auction.

<a id="ac2"></a>
### AC2: Buyer

A person intending to purchase a product at an auction..


## User level use cases

### Actors and their goals 

[Seller](#ac1):
* [UC1](#uc1): Offering a product at an auction
* [UC2](#uc2): Transfering the product to the Buyer

[Buyer](#ac2):
* [UC3](#uc3) Offering a bid for the product that is higher than the currently highest bid. ([BR1](#br1))
* [UC4](#uc4) Wining the auction ([BR2](#br2))
* [UC5](#uc5) Transferng the amount due to the Seller.

---
<a id="uc1"></a>
### UC1: Offering a product at an auction

**Actors:** [Seller](#ac1)

**Main scenario:**
1. [Seller](#ac1) reports to the system the willingness to offer the product up at an auction.
2. System asks for the product data and initial price.
3. [Seller](#ac1) provides product data and the initial price.
4. System verifies data correctness.
5. System informs that the product has been successfully put up for auction.

**Alternative scenarios:** 

4.A. Incorrect or incomplete product data has been entered.
* 4.A.1. informs about incorrectly entered data.
* 4.A.2. Continue at step 2.

---

<a id="uc2"></a>
### UC2: Transfering the product to the Buyer

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Main scenario:**
1. Buyer identifies a product of interest in the auction listings
2. Buyer decides to participate in the auction for the identified product
3. Buyer enters a bid higher than the current highest bid, ensuring it exceeds the current bid by at least EUR 1.00 (BR1)
4. System validates the bid
5. If the bid is valid, the system updates the current highest bid

**Alternative scenarios:** 

3.A. If Buyer decides not to increase their bid
* 3.A.1. Buyer remains outbid, and the auction continues

5.A. If the bid is not valid (e.g., it does not exceed the current highest bid by at least EUR 1.00)
* 5.A.1. The system notifies Buyer that their bid is invalid
* 5.A.2. Buyer is prompted to enter a new bid

---

<a id="uc3"></a>
### UC3: Offering a Bid for the Product

**Actors:** [Buyer](#ac2)

**Main scenario:**
1. Buyer reviews the auction listings and finds a product of interest
2. Buyer submits a bid higher than the current highest bid, adhering to the bidding rules (BR1)
3. System verifies the bid and updates the current highest bid if the submitted bid is valid
4. Buyer receives confirmation of the successful bid submission

**Alternative scenarios:** 

2.A. Buyer submits a bid lower than the current highest bid
* 2.A.1. System rejects the bid and notifies Buyer to submit a higher bid
* 2.A.2. End of use case

---

<a id="uc4"></a>
### UC4: Winning the Auction

**Actors:** [Buyer](#ac2)

**Main scenario:**
1. The auction ends either due to the expiration of the auction time or no further bids
2. If Buyer has the highest bid at the end of the auction, they win the auction (BR2)
3. System notifies Buyer of their winning status
4. Buyer proceeds to pay the due amount to the Seller (UC5)

**Alternative scenarios:** 

2.A. Another bidder submits a higher bid before the auction ends.
* 2.A.1. Buyer loses the auction.
* 2.A.2. End of use case.

---

<a id="uc5"></a>
### UC5: Transferring the Amount Due to the Seller

**Actors:**  [Seller](#ac1), [Buyer](#ac2)

**Main scenario:**
1. Buyer initiates the payment process after winning the auction
2. Seller verifies the received payment
3. Buyer transfers the due amount to the Seller through the specified payment method
4. Upon successful verification, Seller updates the system to confirm the receipt of payment

**Alternative scenarios:** 

3.A. Payment transfer fails due to technical issues.
* 3.A.1. Buyer retries the payment process.
* 3.A.2. If the issue persists, Buyer contacts the support team.
* 3.A.3. End of use case.

---
## Business objects (also known as domain or IT objects)

### BO1: Auction

The auction is a form of concluding a sale and purchase transaction in which the Seller specifies the starting bid of the product, while the Buyers may offer their own purchase offer, each time proposing a bid higher than the currently offered bid. The auction ends after a specified period of time. If at least one purchase offer has been submitted, the product is purchased by the Buyer who offered the highest bid. 

### BO2: Product

A physical or digital item to be auctioned.

## Business rules

<a id="br1"></a>
### BR1: Bidding at auction

Bidding at auction requires submitting an amount higher than current by a minimum of EUR 1.00

<a id="br2"></a>
### BR2: Winning an auction

Auction is won by [Buyer](#ac2) who submitted the highest bid before the end of the auction (time expires).


## CRUDL Matrix


| Use case                                  | Auction | Product | User |
| ----------------------------------------- | ------- | ------- | -----|
| UC1: Offering a product at an auction     |    C    |    C    |      |
| UC2: Transfering the product to the Buyer |    U    |    R    |   R  |
| UC3: Offering a bid for the product       |    U    |         |   R  |
| UC4: Winning the auction                  |    U    |         |      |
| UC5: Transferring the amount              |         |         |   R  |

<a id="uc6"></a>

### UC6: Managing auctions
**Actors:** [Seller](#ac1)

**Description:** Allows the Seller to manage their active auctions, including modifying auction details or canceling auctions.

<a id="uc7"></a>

### UC7: Searching for products
**Actors:** [Buyer](#ac2)

**Description:** Allows the Buyer to search for specific products within the auction listings.

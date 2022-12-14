const input = require('sync-input');

const printWelcomeMessage = () => {
    console.log(`WELCOME TO THE CARNIVAL GIFT SHOP!
Hello friend! Thank you for visiting the carnival!`)
};

gifts = {
    1: {name: "Teddy Bear", price: 10},
    2: {name: "Big Red Ball", price: 5},
    3: {name: "Huge Bear", price: 50},
    4: {name: "Candy", price: 8},
    5: {name: "Stuffed Tiger", price: 15},
    6: {name: "Stuffed Dragon", price: 30},
    7: {name: "Skateboard", price: 100},
    8: {name: "Toy Car", price: 25},
    9: {name: "Basketball", price: 20},
    10: {name: "Scary Mask", price: 75}
};

const showGifts = () => {
    console.log("Here's the list of gifts:\n");
    if (Object.keys(gifts).length === 0) {
        console.log("Wow! There are no gifts to buy.");
        return;
    }
    for (const [id, data] of Object.entries(gifts)) {
        console.log(`${id}- ${data.name}, Cost: ${data.price} tickets`)
    }
};

const printMenu = () => {
    console.log(`
What do you want to do?
1-Buy a gift 2-Add tickets 3-Check tickets 4-Show gifts 5-Exit the shop`)
};

let tickets = 0;

const buyGift = () => {
    if (Object.keys(gifts).length === 0) {
        console.log("Wow! There are no gifts to buy.");
        return;
    }

    const giftId = Number(input(("Enter the number of the gift you want to get: ")));

    if (Number.isNaN(giftId)) {
        console.log("Please enter a valid number!");
        return;
    }

    if (!(giftId in gifts)) {
        console.log("There is no gift with that number!");
        return;
    }

    if (gifts[giftId].price > tickets) {
        console.log("You don't have enough tickets to buy this gift.");
        return;
    }

    console.log(`Here you go, one ${gifts[giftId].name}!`);
    tickets -= gifts[giftId].price;
    checkTickets();
    delete gifts[giftId];
}

const addTickets = () => {
    const toAdd = Number(input("Enter the ticket amount: "));
    if (Number.isNaN(toAdd) || toAdd < 0 || toAdd > 1000) {
        console.log("Please enter a valid number between 0 and 1000.");
        return;
    }
    tickets += toAdd;
    checkTickets();
};

const checkTickets = () => {
    console.log(`Total tickets: ${tickets}`);
    printHaveANiceDay();
};

const printHaveANiceDay = () => {
    console.log("Have a nice day!");
}

const handleChoice = () => {
    let isRunning = true;

    while (isRunning) {
        printMenu();
        const choice = input();

        switch (choice) {
            case "1":
                buyGift();
                break;
            case "2":
                addTickets();
                break;
            case "3":
                checkTickets();
                break;
            case "4":
                showGifts();
                break;
            case "5":
                isRunning = false;
                break;
            default:
                console.log("Please enter a valid number!");
                break;
        }
    }
    printHaveANiceDay();
}

printWelcomeMessage();
showGifts();
handleChoice();
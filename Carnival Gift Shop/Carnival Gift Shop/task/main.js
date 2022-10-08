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
    for (const [id, data] of Object.entries(gifts)) {
        console.log(`${id}- ${data.name}, Cost: ${data.price} tickets`)
    }
};

const printMenu = () => {
    console.log(`
What do you want to do?
1-Buy a gift 2-Add tickets 3-Check tickets 4-Show gifts`)
};

let tickets = 100;

const isValid = (input) => {
    if (Number.isNaN(input)) {
        console.log("Wrong input.");
        return false;
    }
    return true;
}

const buyGift = () => {
    const giftId = Number(input(("Enter the number of the gift you want to get: ")));

    if (!isValid(giftId)) return;
    if (giftId in gifts) {
        console.log(`Here you go, one ${gifts[giftId].name}!`);
        tickets -= gifts[giftId].price;
        checkTickets();
    } else {
        console.log(`There's no gift with id: ${giftId}.`);
    }
}

const addTickets = () => {
    const toAdd = Number(input("Enter the ticket amount: "));
    if (!isValid(toAdd)) return;
    tickets += toAdd;
    checkTickets();
};

const checkTickets = () => {
    console.log(`Total tickets: ${tickets}
Have a nice day!`);
};

const handleChoice = () => {
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
            console.log("Have a nice day!");
            break;
        default:
            console.log("Not available option.");
            break;
    }
}

printWelcomeMessage();
showGifts();
handleChoice();
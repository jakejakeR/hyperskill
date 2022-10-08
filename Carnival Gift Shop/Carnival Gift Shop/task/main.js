const welcomeMessage = () => {
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

welcomeMessage();
showGifts();
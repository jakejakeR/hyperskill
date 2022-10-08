const welcomeMessage = () => {
    console.log(`WELCOME TO THE CARNIVAL GIFT SHOP!
Hello friend! Thank you for visiting the carnival!`)
};

gifts = {
    1: {name: "Teddy Bear"},
    2: {name: "Big Red Ball"},
    3: {name: "Huge Bear"},
    4: {name: "Candy"},
    5: {name: "Stuffed Tiger"},
    6: {name: "Stuffed Dragon"},
    7: {name: "Skateboard"},
    8: {name: "Toy Car"},
    9: {name: "Basketball"},
    10: {name: "Scary Mask"}
};

const showGifts = () => {
    console.log("Here's the list of gifts:\n");
    Object.values(gifts).forEach(
        gift => console.log(gift.name)
    );
};

welcomeMessage();
showGifts();
## Process book
## Programmeerproject
### By Levy van der Linde
### Minor Programmeren

*What’s in your fridge?*

### Processbook

#### Day 1
Made a proposal for my app idea. At first I had this idea with personalizing cakes but couldn't find a fitting API for cake recipes. 
After that I started brainstorming and came up with the idea about 'what's in your fridge?'.

#### Day 2
I worked on my design document. This was about thinking about the more technical aspects of my app. 
I thought about how to design the UI's and make sure screens get properly connected. I created all activities with buttons/text etc. 
This was my advanced sketch at day 2:

<img width="500" alt="image" src="https://user-images.githubusercontent.com/47352487/58959822-2e24df00-87a6-11e9-8f9d-98d69b17155f.png">

### Day 3
Thinking about the app being user friendly, I made some changes. 

First of all I decided to try and create one SearchActivity which handles the API search as well as the DB search. 
This way the user doesn't need to search twice but get's both results at once. 

Next to this, I made some name changes in the activities. 
Also there was the GroceryActivity which shows a GroceryList. I thought of creating a function where the user can add a found recipe to their favorites. 
At the first screen it'll be possible to see your favorites so you can remember them locally on your phone to look up in te future.

The app sketch changed to this:

<img width="500" alt="image" src="https://user-images.githubusercontent.com/47352487/58960665-eacb7000-87a7-11e9-9723-5efe525bedaa.png">

After changing the technical aspects I started programming this set up in Android Studio. 

### Day 4
Still working on connecting all the screens and creating buttons etc. Also making sure the Food2Fork API works. 

### Day 5
The API works! The APISearchRequest data is printed containing the name, id and image urls. The next step is to use this for a APIRecipeRequest. Because then the complete recipes will be requested so the ingredients etc too. This is the data that will be shown in the ResultsActivity.

# PetCareBackend

Project Was based off PetCare repository which is a pet form reigastration app. Uses RoomDatabase as a layer over SQLite.

The app layout is familiar to that of a google doc survey, very simple and easy to fill out
with a few TextEdits that are required to have valid data. PetCare was to test our ability to
create an app and make it so the form registration would not be "submitted" with improper data,
such as a microchip ID that already exists in the list array, or that does not have all caps.

PetCareBackend takes it a step further by adding a database to that app and putting the
microchip IDs that were in the string array into the database. When the list is needed to compare
the microchips IDs in the database to the one entered, a query that grabs the Microchip ID coloumn
from the PetInfo entity is used. Once you hit submit, if valid data was entered in the required boxes,
then your registration will be saved to the database.

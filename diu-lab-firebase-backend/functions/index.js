const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp();

const firestoreDb = admin.firestore();


function sendNotification(userInfo, complain) {
    let registrationToken = userInfo.token;

    if(!userInfo.token){
        console.log("user don't have any token to send notification");
        return false;
    }

// See documentation on defining a message payload.
    let message = {
        data: {
            title: complain.complain_description,
            description: complain.complain_type,
            user_id: complain.from_user_id,
            date_time: '10-12-1222'
        },
        token: registrationToken
    };

// Send a message to the device corresponding to the provided
// registration token.
  return   admin.messaging().send(message).then((response) => {
        // Response is a message ID string.
        console.log('Successfully sent message:', response);
        return true ;
    }).catch((error) => {
        console.log('Error sending message:', error);
        return true;
    });


}

exports.newComplain = functions.firestore.document('complains/{complainId}').onCreate((snapshot, context)=>{
    /**
     # Complain Properties
     # {complain_description, complain_type, computer_id, from_user_id, room_id }
     #**/

    /** Getting the complain from the snapshot that had been created now **/
    let complain = snapshot.data();
    console.log(complain);


    let userCollection = firestoreDb.collection('users');

    userCollection.where('user_type', '==', 'Authority').get()
        .then(snapshot => {
            if(snapshot){
                snapshot.forEach(doc => {
                    console.log(doc.id, '=>', doc.data());
                    if(doc.data()){
                        sendNotification(doc.data(), complain);
                    }


                });
            }
            else{
                console.log("No user found.");
            }

            return true ;
        })
        .catch(err => {
            console.log('Error getting users', err);
        });


});


import axios from "axios";
import authHeader from "./auth-header";
import AuthService from "../services/auth.service";

let API_URL = "http://localhost:8080/api/";
let userRole;

if (AuthService.getCurrentUser()) {
  userRole = AuthService.getCurrentUser().roles[0];
  if (userRole === "ROLE_STUDENT") {
    API_URL = API_URL + "s/";
  } else if (userRole === "ROLE_INSTRUKTOR") {
    API_URL = API_URL + "i/";
  } else if (userRole === "ROLE_ADMIN") {
    API_URL = API_URL + "a/";
  }
}

export default userRole;

export function getInstruktorList() {
  return axios.get(API_URL + "instruktori/", { headers: authHeader() });
}

export function getStudentList() {
  return axios.get(API_URL + "studenti/", { headers: authHeader() });
}

export function getInstruktorByID(id) {
  return axios.get(API_URL + "instruktor/id=" + id, {
    headers: authHeader(),
  });
}

export function getCalendarByID(id) {
  return axios.get(API_URL + "kalendar/id=" + id, {
    headers: authHeader(),
  });
}

export function getMyCalendar() {
  return axios.get(API_URL + "kalendar", {
    headers: authHeader(),
  });
}

// export function sendNewTermin(
//   year,
//   month,
//   day,
//   startHour,
//   startMinute,
//   endHour,
//   endMinute
// ) {
//   return axios.post(
//     API_URL + "kalendar",
//     {
//       year,
//       month,
//       day,
//       startHour,
//       startMinute,
//       endHour,
//       endMinute
//     },
//     {
//       headers: authHeader(),
//     }
//   ).then(response => {

//     let statusCode = response.status,
//         success = response.ok;
//     console.log(response.data.message)
//     response.json().then(response => {

//         if(!success){
//             //handle errors here
//             // console.log(response.message)
//             console.log(response.data)
//             return;
//         }

//         // handle successful requests here
//         console.log(response.message)
//         window.location.href = "/kalendar";
//     })
// }).catch((error) => {
//     // catch any unexpected errors
//     console.log(error);
// });
// }


export function sendNewTermin(
  year,
  month,
  day,
  startHour,
  startMinute,
  endHour,
  endMinute
) {
  return axios.post(
    API_URL + "kalendar",
    {
      year,
      month,
      day,
      startHour,
      startMinute,
      endHour,
      endMinute
    },
    {
      headers: authHeader(),
    }
  );
}

export function getStudentByID(id) {
  return axios.get(API_URL + "student/id=" + id, {
    headers: authHeader(),
  });
}

export function setFavoritByID(id) {
  return axios.post(
    API_URL + "favourites/add/id=" + id,
    {},
    {
      headers: authHeader(),
    }
  );
}

export function removeFavoritByID(id) {
  return axios.post(
    API_URL + "favourites/remove/id=" + id,
    {},
    {
      headers: authHeader(),
    }
  );
}

export function deleteInstructorByID(id) {
  return axios.delete(API_URL + "instruktori/id=" + id, {
    headers: authHeader(),
  });
}

export function deleteStudentByID(id) {
  return axios.delete(API_URL + "studenti/id=" + id, {
    headers: authHeader(),
  });
}

export function getListFavourites() {
  return axios.get(API_URL + "favourites", {
    headers: authHeader(),
  });
}

export function getMyData() {
  return axios.get(API_URL + "instruktor/profil", { headers: authHeader() });
}
export function getMyDataStudent() {
  return axios.get(API_URL + "currentStudent", { headers: authHeader() });
}

export function sendEditedData(
  id,
  firstName,
  lastName,
  mobileNum,
  adress,
  opis,
  predmeti
) {
  return axios.post(
    API_URL + "instruktor/update/id=" + id,
    {
      firstName,
      lastName,
      mobileNum,
      adress,
      opis,
      predmeti,
    },
    {
      headers: authHeader(),
    }
  );
}

export function getTestovi() {
  return axios.post(
    "http://localhost:8080/api/ispit/dohvati-ispit",
    {
      razred: "1ss",
      predmet: "MATEMATIKA",
    },
    { headers: authHeader() }
  );
}

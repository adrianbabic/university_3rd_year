import React, { Component } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/auth.service";
import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";
import EventBus from "./common/EventBus";
import InstructorList from "./components/instructorsList.component";
import Karta from "./components/karta.component";
import Kalendar from "./components/kalendar.component";
import InstruktorStranica from "./components/instruktorStranica";
import EditProfile from "./components/editProfile";
import userRole from "./services/instruktor.service";
import StudentList from "./components/studentsList.component";
import StudentStranica from "./components/studentStranica";
import ProfileStudent from "./components/profile.student.component";
import Testovi from "./components/testovi.component";
import KalendarPregled from "./components/kalendarPregled";

class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
      });
    }

    EventBus.on("logout", () => {
      this.logOut();
    });
  }

  componentWillUnmount() {
    EventBus.remove("logout");
  }

  logOut() {
    AuthService.logout();
    this.setState({
      currentUser: undefined,
    });
  }

  render() {
    const { currentUser, showAdminBoard } = this.state;

    // treba promjenit navbar u hamburger

    return (
      <div>
        <nav className="navbar navbar-expand navbar-light navbar-custom">
          <Link to={"/"} className="navbar-brand">
            Centar Instrukcija
          </Link>
          <div className="navbar-nav mr-auto">
            {currentUser && userRole === "ROLE_INSTRUKTOR" && (
              <li className="nav-item">
                <Link to={"/profile"} className="nav-link">
                  Profil
                </Link>
              </li>
            )}
            {currentUser && userRole === "ROLE_STUDENT" && (
              <li className="nav-item">
                <Link to={"/profileStudent"} className="nav-link">
                  Profil
                </Link>
              </li>
            )}

            {currentUser && userRole === "ROLE_ADMIN" && (
              <li className="nav-item">
                <Link to={"/studenti"} className="nav-link">
                  Studenti
                </Link>
              </li>
            )}

            {currentUser && (
              <li className="nav-item">
                <Link to={"/instruktori"} className="nav-link">
                  Instruktori
                </Link>
              </li>
            )}

            {currentUser && userRole === "ROLE_INSTRUKTOR" && (
              <li className="nav-item">
                <Link to={"/kalendar"} className="nav-link">
                  Moj kalendar
                </Link>
              </li>
            )}

            {currentUser && userRole === "ROLE_STUDENT" && (
              <li className="nav-item">
                <Link to={"/karta"} className="nav-link">
                  Karta
                </Link>
              </li>
            )}
          </div>

          {currentUser ? (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={this.logOut}>
                  Odjava
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link">
                  Prijava
                </Link>
              </li>

              <li className="nav-item">
                <Link to={"/register"} className="nav-link">
                  Registracija
                </Link>
              </li>
            </div>
          )}
        </nav>

        <div className="container mt-3">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/home" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/profileStudent" element={<ProfileStudent />} />
            <Route path="/instruktori" element={<InstructorList />} />
            <Route path="/instruktori/*" element={<InstruktorStranica />} />

            <Route path="/studenti" element={<StudentList />} />
            <Route path="/studenti/*" element={<StudentStranica />} />
            <Route path="/testovi" element={<Testovi />} />
            <Route path="/kalendar" element={<Kalendar />} />
            <Route path="/karta" element={<Karta />} />
            <Route path="/edit-profile" element={<EditProfile />} />
            <Route path="/kalendar/*" element={<KalendarPregled />} />
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;

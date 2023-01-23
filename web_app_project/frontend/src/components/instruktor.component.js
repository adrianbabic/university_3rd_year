import React, { Component } from "react";
import { Navigate } from "react-router-dom";
import instruktorService from "../services/instruktor.service";

const capitalizeFirst = (str) => {
  return str.charAt(0).toUpperCase() + str.slice(1);
};

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
    };
  }

  componentDidMount() {
    let currentInstruktor = InstruktorService.getInstruktorByID(id);
    const currentUser = AuthService.getCurrentUser();

    if (!currentUser) this.setState({ redirect: "/home" });
    this.setState({ currentUser: currentUser, userReady: true });
  }

  render() {
    if (this.state.redirect) {
      return <Navigate to={this.state.redirect} />;
    }

    const { currentUser } = this.state;

    return (
      <div className="container">
        {this.state.userReady ? (
          <div>
            <header className="jumbotron">
              <h1>
                Bok <strong>{capitalizeFirst(currentUser.firstName)}</strong>!
              </h1>
            </header>
            <br></br>
            <h2>Tvoji podaci:</h2>
            <br></br>
            <p>
              <strong>Email:</strong> {currentUser.email}
            </p>
            <strong>Tvoje role u applikaciji:</strong>
            <ul>
              {currentUser.roles &&
                currentUser.roles.map((role, index) => (
                  <li key={index}>{role}</li>
                ))}
            </ul>
          </div>
        ) : null}
      </div>
    );
  }
}

import React, { Component, useState } from "react";
import { getInstruktorList } from "../services/instruktor.service";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import { Link } from "react-router-dom";
import { BsStarFill, BsStar } from "react-icons/bs";
import userRole from "../services/instruktor.service";

const favoritStyle = {
  justifySelf: "center",
  alignSelf: "center",
  marginLeft: "15px",
  marginBottom: "6px",
  color: "orange",
};

const InstructorCard = (props) => {
  const element = props.element; //
  console.log(props.favourites);
  let favoritFlag;

  if (userRole === "ROLE_STUDENT") {
    favoritFlag = props.favourites
      .map((element) => element.id)
      .includes(element.id);
  }

  return (
    <>
      <Link to={"/instruktori/id=" + element.id}>
        <button className="ButtonInstruktor">
          <li key={element.id} className="Instruktor">
            <div>
              <div className="instruktorIme">
                <h4>
                  {element.firstName} {element.lastName}
                </h4>
                {userRole === "ROLE_STUDENT" ? (
                  <>
                    {favoritFlag ? (
                      <BsStarFill style={favoritStyle} />
                    ) : (
                      <BsStar style={favoritStyle} />
                    )}
                  </>
                ) : (
                  <></>
                )}
              </div>
              <div className="instruktorDetalji">
                <h6>
                  E-Mail: <small>{element.email}</small>
                </h6>

                <h6>
                  Broj telefona:{" "}
                  {element.mobileNum ? (
                    <small>{element.mobileNum} </small>
                  ) : (
                    <small>/</small>
                  )}
                </h6>

                <h6>
                  Adresa:{" "}
                  {element.adresa ? (
                    <small>{element.adresa} </small>
                  ) : (
                    <small>/</small>
                  )}
                </h6>
              </div>
            </div>

            <div
              className="slikaLista"
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
              }}
            >
              {element.image ? (
                <img
                  src="element.image"
                  alt="profile-img"
                  className="profile-img-card"
                />
              ) : (
                <img
                  src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                  alt="profile-img"
                  className="profile-img-card"
                />
              )}
            </div>
          </li>
        </button>
      </Link>
    </>
  );
};
export default class InstructorList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: "",
      redirect: null,
      userReady: false,
    };
  }

  componentDidMount() {
    let currentUser = AuthService.getCurrentUser();

    if (!currentUser) {
      this.setState({ redirect: "/home" });
    } else {
      getInstruktorList().then(
        (response) => {
          this.setState({
            favourites: response.data.favourites,
            usersArray: response.data.allInstructors
              .filter((element) => element.id != currentUser.id)
              .map((element) => (
                <InstructorCard
                  element={element}
                  favourites={response.data.favourites}
                />
              )),
          });
        },
        (error) => {
          console.log(error);
          this.setState({
            content:
              (error.response && error.response.data) ||
              error.message ||
              error.toString(),
          });
        }
      );
    }
    this.setState({ currentUser: currentUser, userReady: true });
  }

  render() {
    if (this.state.redirect) {
      return <Navigate to={this.state.redirect} />;
    }

    const { currentUser } = this.state;

    // localStorage.setItem(JSON.parse(this.state.favourites))

    return (
      <div className="container">
        {userRole == "ROLE_INSTRUKTOR" && (
          <div>
            <h3>Ostali instruktori: </h3>
          </div>
        )}

        <ul className="ListInstruktora">{this.state.usersArray}</ul>
      </div>
    );
  }
}

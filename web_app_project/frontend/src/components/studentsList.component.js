import React, { Component } from "react";
import { getStudentList } from "../services/instruktor.service";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import { Link } from "react-router-dom";
import userRole from "../services/instruktor.service";

const favoritStyle = {
  justifySelf: "center",
  alignSelf: "center",
  marginLeft: "15px",
  marginBottom: "6px",
  color: "orange",
};

const StudentCard = (props) => {
  const element = props.element;
  return (
    <>
      <Link to={"/studenti/id=" + element.id}>
        <button className="ButtonInstruktor">
          <li key={element.id} className="Instruktor">
            <div>
              <div className="instruktorIme">
                <h4>
                  {element.firstName} {element.lastName}
                </h4>
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
export default class StudentList extends Component {
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

    if (!currentUser || userRole != "ROLE_ADMIN") {
      this.setState({ redirect: "/home" });
    } else {
      getStudentList().then(
        (response) => {
          this.setState({
            usersArray: response.data.allStudents.map((element) => (
              <StudentCard element={element} />
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

    return (
      <div className="container">
        <ul className="ListInstruktora">{this.state.usersArray}</ul>
      </div>
    );
  }
}

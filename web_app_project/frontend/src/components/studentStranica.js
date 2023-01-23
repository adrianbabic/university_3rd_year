import React, { Component, useState } from "react";
import {
  getStudentByID,
  deleteStudentByID,
} from "../services/instruktor.service";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import { TiDelete } from "react-icons/ti";
import userRole from "../services/instruktor.service";
import styled from "styled-components";

const capitalizeFirst = (str) => {
  return str.charAt(0).toUpperCase() + str.slice(1);
};

const deleteStyle = {
  justifySelf: "center",
  alignSelf: "center",
  marginLeft: "50px",
  marginBottom: "6px",
  width: "4rem",
  height: "4rem",
  color: "red",
};

const PopupContainer = styled.div`
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
`;

const PopupContent = styled.div`
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  width: 40%;
  h2 {
    margin-bottom: 20px;
  }
  div {
    display: flex;
    justify-content: space-around;
    button {
      width: 30%;
      padding: 10px 20px;
      border-radius: 5px;
      border: none;
      background-color: blue;
      color: white;
      font-weight: bold;
      &:hover {
        cursor: pointer;
      }
    }
  }
`;

const StudentProfileView = (props) => {
  const element = props;
  const [redirect, setRedirect] = useState(false);

  let currentUser = AuthService.getCurrentUser();

  const [showConfirmPopup, setShowConfirmPopup] = useState(false);

  const toggleDeleteStudent = () => {
    setShowConfirmPopup(!showConfirmPopup);
  };

  const [isDeleting, setIsDeleting] = useState(false);
  const [deleteSuccess, setDeleteSuccess] = useState(false);

  const handleDelete = async () => {
    setIsDeleting(true);
    try {
      await deleteStudentByID(element.id);
      setDeleteSuccess(true);
    } catch (error) {
      console.log(error);
      setDeleteSuccess(false);
    } finally {
      setIsDeleting(false);
    }
  };

  if (deleteSuccess) {
    return <Navigate to={"/studenti"} />;
  }

  return (
    <>
      {isDeleting && (
        <PopupContainer>
          <PopupContent>
            <div style={{ alignSelf: "center" }}>
              <h2>Učitavanje...</h2>
            </div>
          </PopupContent>
        </PopupContainer>
      )}

      {showConfirmPopup && (
        <PopupContainer>
          <PopupContent>
            <div style={{ alignSelf: "center" }}>
              <h2>Želite li izbrisati ovog studenta?</h2>
            </div>
            <div>
              <button onClick={handleDelete}>Da</button>
              <button onClick={() => setShowConfirmPopup(false)}>Ne</button>
            </div>
          </PopupContent>
        </PopupContainer>
      )}
      <div className="container">
        <header className="jumbotron">
          <div className="instruktorIme">
            <h1
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "flex-start",
                textTransform: "capitalize",
                marginLeft: "40px",
              }}
            >
              <strong>
                {element.firstName} {element.lastName}
              </strong>
            </h1>

            {userRole === "ROLE_ADMIN" ? (
              <TiDelete
                style={deleteStyle}
                onClick={() => {
                  toggleDeleteStudent();
                }}
              />
            ) : (
              <></>
            )}
          </div>
          <br></br>
          <br></br>
          <div
            style={{
              display: "flex",
              flexDirection: "row",
              justifyContent: "space-around",
            }}
          >
            <div
              style={{
                alignItems: "left",
                justifyContent: "left",
              }}
            >
              <h4>Podaci o studentu:</h4>
              <br></br>
              <p>
                <strong>Email:</strong> {element.email}
              </p>
              <p>
                <strong>Adresa:</strong>{" "}
                {element.adresa ? (
                  <small>{element.adresa}</small>
                ) : (
                  <small>/</small>
                )}
              </p>
              <p>
                <strong>Broj telefona:</strong>{" "}
                {element.mobileNum ? (
                  <small>{element.mobileNum} </small>
                ) : (
                  <small>/</small>
                )}
              </p>
              <p>
                <strong>Rijesenost ispita do razreda:</strong>{" "}
                {element.maxRazred ? (
                  <small>{element.maxRazred}</small>
                ) : (
                  <small>/</small>
                )}
              </p>
            </div>
            <div
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
                  className="profile-img-card2"
                />
              ) : (
                <img
                  src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                  alt="profile-img"
                  className="profile-img-card2"
                />
              )}
            </div>
          </div>
        </header>
      </div>
    </>
  );
};
export default class StudentProfile extends Component {
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
    this.setState({ id: window.location.href.split("=").reverse()[0] });

    if (!currentUser) {
      this.setState({ redirect: "/home" });
    } else {
      getStudentByID(window.location.href.split("=").reverse()[0]).then(
        (response) => {
          this.setState({
            student: response.data,
            notFound: false,
          });
        },
        (error) => {
          this.setState({
            notFound: true,
          });
        }
      );

      this.setState({ currentUser: currentUser, userReady: true });
    }
  }

  render() {
    if (this.state.redirect) {
      return <Navigate to={this.state.redirect} />;
    } else if (this.state.notFound) {
      return <Navigate to={"/studenti"} />;
    } else {
      return <StudentProfileView {...this.state.student} />;
    }
  }
}

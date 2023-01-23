import React, { Component, useState, useEffect } from "react";
import {
  getInstruktorByID,
  setFavoritByID,
  removeFavoritByID,
  deleteInstructorByID,
} from "../services/instruktor.service";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import { BsStarFill, BsStar } from "react-icons/bs";
import { TiDelete } from "react-icons/ti";
import userRole from "../services/instruktor.service";
import { getInstruktorList } from "../services/instruktor.service";
import styled from "styled-components";

const capitalizeFirst = (str) => {
  return str.charAt(0).toUpperCase() + str.slice(1);
};

const favoritStyle = {
  justifySelf: "center",
  alignSelf: "center",
  marginLeft: "50px",
  marginBottom: "6px",
  width: "2rem",
  height: "2rem",
  color: "orange",
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

const InstructorProfileView = (props) => {
  const element = props;
  const [favorit, setFavorit] = useState(false);
  const [redirect, setRedirect] = useState(false);

  let currentUser = AuthService.getCurrentUser();

  useEffect(() => {
    async function fetchData() {
      const response = await getInstruktorList();
      const favourites = response.data.favourites;
      if (userRole === "ROLE_STUDENT" && favourites !== undefined) {
        const isFavorit = favourites.map((f) => f.id).includes(element.id);
        setFavorit(isFavorit);
      }
    }
    fetchData();
  }, [element.id, userRole]);

  const toggleFavourite = () => {
    favorit
      ? removeFavoritByID(element.id).then(setFavorit(!favorit))
      : setFavoritByID(element.id).then(setFavorit(!favorit));
  };

  const getCalendar = (e) => {
    e.preventDefault();
    window.location.href = "/kalendar/id=" + element.id;
  };

  const [showConfirmPopup, setShowConfirmPopup] = useState(false);

  const toggleDeleteInstruktor = () => {
    setShowConfirmPopup(!showConfirmPopup);
  };

  const [isDeleting, setIsDeleting] = useState(false);
  const [deleteSuccess, setDeleteSuccess] = useState(false);

  const handleDelete = async () => {
    setIsDeleting(true);
    try {
      await deleteInstructorByID(element.id);
      setDeleteSuccess(true);
    } catch (error) {
      console.log(error);
      setDeleteSuccess(false);
    } finally {
      setIsDeleting(false);
    }
  };

  if (deleteSuccess) {
    return <Navigate to="/instruktori" />;
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
              <h2>Želite li izbrisati ovog instruktora?</h2>
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
            {userRole === "ROLE_STUDENT" ? (
              <>
                {favorit === true ? (
                  <BsStarFill
                    style={favoritStyle}
                    onClick={() => {
                      toggleFavourite();
                    }}
                  />
                ) : (
                  <BsStar
                    style={favoritStyle}
                    onClick={() => {
                      toggleFavourite();
                    }}
                  />
                )}
              </>
            ) : (
              <></>
            )}

            {userRole === "ROLE_ADMIN" ? (
              <TiDelete
                style={deleteStyle}
                onClick={() => {
                  toggleDeleteInstruktor();
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
              <h4>Podaci o instruktoru:</h4>
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
              <div>
              <button
                type="submit"
                id="submit"
                name="submit"
                className="btn btn-primary"
                onClick={getCalendar}
              >
                Provjeri raspored instruktora
              </button>
            </div>
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

export default class InstructorProfile extends Component {
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
      getInstruktorByID(window.location.href.split("=").reverse()[0]).then(
        (response) => {
          this.setState({
            instruktor: response.data,
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
      return <Navigate to={"/instruktori"} />;
    } else {
      return <InstructorProfileView {...this.state.instruktor} />;
    }
  }
}

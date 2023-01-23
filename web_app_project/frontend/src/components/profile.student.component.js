import React, { Component, useState } from "react";
import * as ReactDOM from "react-dom";
import { Navigate, Link } from "react-router-dom";
import AuthService from "../services/auth.service";
import {
  getMyData,
  getMyDataStudent,
  sendEditedData,
} from "../services/instruktor.service";
import "../css/profile.css";

const ProfileView = (props) => {
  const handleEdit = (e) => {
    e.preventDefault();
    window.location.href = "/edit-profile";
  };

  return (
    <div>
      <link
        href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
        rel="stylesheet"
        id="bootstrap-css"
      ></link>

      <div className="container emp-profile">
        <form method="post">
          <div className="row">
            <div className="col-md-4">
              <div className="profile-img"></div>
            </div>
            <div className="col-md-6">
              <div className="profile-head">
                <h4>{props.firstName}</h4>
                <h6>Student</h6>

                <ul className="nav nav-tabs" id="myTab" role="tablist">
                  <li className="nav-item">
                    <a
                      className="nav-link active"
                      id="home-tab"
                      data-toggle="tab"
                      href="#home"
                      role="tab"
                      aria-controls="home"
                      aria-selected="true"
                    >
                      O meni
                    </a>
                  </li>
                </ul>
              </div>
            </div>
            <div className="col-md-2"></div>
          </div>
          <div className="row">
            <div className="col-md-4">
              <div className="profile-work"></div>
            </div>
            <div className="col-md-8">
              <div className="tab-content profile-tab" id="myTabContent">
                <div
                  className="tab-pane fade show active"
                  id="home"
                  role="tabpanel"
                  aria-labelledby="home-tab"
                >
                  <div className="row">
                    <div className="col-md-6">
                      <label>Ime</label>
                    </div>
                    <div className="col-md-6">
                      <p>{props.firstName}</p>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-md-6">
                      <label>Prezime</label>
                    </div>
                    <div className="col-md-6">
                      <p>{props.lastName}</p>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-md-6">
                      <label>Email</label>
                    </div>
                    <div className="col-md-6">
                      <p>{props.email}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default class ProfileStudent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
    };
  }

  componentDidMount() {
    let currentUser = AuthService.getCurrentUser();
    this.setState();

    if (!currentUser) {
      this.setState({ redirect: "/home" });
    } else {
      getMyDataStudent().then(
        (response) => {
          this.setState({
            user: response.data,
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
      return <ProfileView {...this.state.user} />;
    }
  }
}

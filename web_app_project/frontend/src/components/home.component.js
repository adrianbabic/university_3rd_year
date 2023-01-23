import React, { Component, Conteiner } from "react";

import Typical from "react-typical";
import "../App.css";

import { getInstruktorList } from "../services/instruktor.service";

export default class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: "",
    };
  }

  componentDidMount() {
    getInstruktorList().then(
      (response) => {
        this.setState({
          content: response.data,
        });
      },
      (error) => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString(),
        });
      }
    );
  }

  render() {
    return (
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          height: "100vh",
        }}
      >
        <p style={{ fontSize: "5rem" }}>
          Mi smo {""}
          <Typical
            loop={Infinity}
            wrapper="b"
            steps={[
              "Centar instrukcija",
              2000,
              "Najjači",
              2000,
              "Progi Enjoyeri",
              2000,
            ]}
          />
        </p>
      </div>
    );
  }
}

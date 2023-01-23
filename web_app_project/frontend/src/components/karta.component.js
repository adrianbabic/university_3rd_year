import React, { Component, useRef } from "react";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import "../css/karta.css";
import { GoogleMap, useLoadScript, Marker } from "@react-google-maps/api";

export default function Home() {
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: "license key",
  });

  if (!isLoaded) return <div>Karta se učitava...</div>;
  return <Map />;
}

function Map() {
  return (
    <GoogleMap
      zoom={12}
      center={{ lat: 45.81501, lng: 15.981919 }}
      mapContainerClassName="map-container1"
    ></GoogleMap>
  );
}

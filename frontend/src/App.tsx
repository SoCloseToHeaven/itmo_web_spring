import React from 'react';
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import AuthorizationPage from "./pages/auth/AuthorizationPage";
import PointsPage from "./pages/points/PointsPage";

function App() {

  return (
      <>
          <Routes>
              <Route path={"/"} element={<AuthorizationPage />} />
              <Route path={"/auth"} element={<AuthorizationPage />} />
              <Route path={"/points"} element={<PointsPage />} />
          </Routes>
      </>
  );
}

export default App;

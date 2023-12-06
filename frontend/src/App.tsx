import React from 'react';
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import AuthorizationPage from "./pages/auth/AuthorizationPage";
import PointsPage from "./pages/points/PointsPage";
import AuthorizedOnly from "./components/AuthorizedOnly";

function App() {

  return (
      <>
          <Routes>
              <Route path={"/"} element={<AuthorizationPage />} />
              <Route path={"/auth"} element={<AuthorizationPage />} />
              <Route
                  path={"/points"}
                  element={<AuthorizedOnly child={<PointsPage />} />}
              />
          </Routes>
      </>
  );
}

export default App;

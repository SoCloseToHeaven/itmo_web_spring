import React from 'react';
import {Route, Routes} from "react-router-dom";
import AuthorizationPage from "./pages/auth/AuthorizationPage";
import PointsPage from "./pages/points/PointsPage";
import AuthorizedRoute from "./pages/auth/components/AuthorizedRoute";

const HOME_PAGE = "/"
const AUTH_PAGE = "/auth"

function App() {



  return (
      <>
          <Routes>
              <Route path={HOME_PAGE} element={<PointsPage />} />
              <Route path={AUTH_PAGE} element={<AuthorizationPage />} />
          </Routes>
      </>
  );
}

export default App;

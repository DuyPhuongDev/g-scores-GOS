import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  async rewrites() {
    return [
      {
        source: "/api/v1/:path*",
        destination: "http://gscores-backend:8888/api/v1/:path*",
      },
    ];
  },
};

export default nextConfig;

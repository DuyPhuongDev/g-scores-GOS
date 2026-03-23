"use client";

import React from "react";
import Link from "next/link";
import { usePathname } from "next/navigation";
import Image from "next/image";
import { Home, FileCheck, FileText } from "lucide-react";
import { cn } from "@/lib/utils";

const navItems = [
  { name: "Trang chủ", href: "/", icon: Home },
  { name: "Tra cứu điểm", href: "/search", icon: FileCheck },
  { name: "Báo cáo", href: "/report", icon: FileText },
];

export function Sidebar() {
  const pathname = usePathname();

  return (
    <aside className="fixed left-0 top-0 h-full w-64 pt-20 bg-slate-50 border-r border-slate-200/50 hidden lg:flex flex-col py-4 z-40">
      <nav className="flex-1 space-y-1 px-2">
        {navItems.map((item) => {
          const isActive = pathname === item.href;
          return (
            <Link
              key={item.name}
              href={item.href}
              className={cn(
                "flex items-center gap-3 px-4 py-3 rounded-lg font-semibold text-sm tracking-wide uppercase transition-all duration-200",
                isActive
                  ? "bg-[#0f2289] text-[#fed800] shadow-md shadow-blue-900/20"
                  : "text-[#0f2289] hover:bg-[#0f2289]/10 hover:translate-x-1",
              )}
            >
              <item.icon className="w-5 h-5" />
              {item.name}
            </Link>
          );
        })}
      </nav>
    </aside>
  );
}

export function TopBar() {
  return (
    <header className="fixed top-0 w-full z-50 h-16 bg-[#0f2289] shadow-lg shadow-blue-900/10 flex justify-between items-center px-6">
      <div className="items-center mx-auto">
        <span className="text-3xl font-bold tracking-tighter text-[#fed800] font-inter">
          G-Scores
        </span>
      </div>
    </header>
  );
}

export function MobileNav() {
  const pathname = usePathname();

  return (
    <div className="md:hidden fixed bottom-0 w-full bg-white shadow-[0_-4px_16px_rgba(0,0,0,0.05)] flex justify-around items-center py-3 z-50 border-t border-slate-100">
      <Link
        href="/"
        className={cn(
          "flex flex-col items-center gap-1",
          pathname === "/" ? "text-primary" : "text-slate-400",
        )}
      >
        <Home className="w-5 h-5" />
        <span className="text-[10px] font-bold">TRANG CHỦ</span>
      </Link>
      <Link
        href="/search"
        className={cn(
          "flex flex-col items-center gap-1",
          pathname === "/search" ? "text-primary" : "text-slate-400",
        )}
      >
        <FileCheck className="w-5 h-5" />
        <span className="text-[10px] font-bold">TRA CỨU</span>
      </Link>
      <Link
        href="/report"
        className={cn(
          "flex flex-col items-center gap-1",
          pathname === "/reports" ? "text-primary" : "text-slate-400",
        )}
      >
        <FileText className="w-5 h-5" />
        <span className="text-[10px] font-bold">BÁO CÁO</span>
      </Link>
    </div>
  );
}

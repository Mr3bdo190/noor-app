import 'package:flutter/material.dart';

void main() {
  runApp(const NoorApp());
}

class NoorApp extends StatelessWidget {
  const NoorApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'نور',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFF38BDF8),
          brightness: Brightness.dark,
          background: const Color(0xFF0F172A),
          surface: const Color(0xFF1E293B),
        ),
      ),
      builder: (context, child) {
        return Directionality(
          textDirection: TextDirection.rtl, // دعم اللغة العربية بالكامل
          child: child!,
        );
      },
      home: const MainScreen(),
    );
  }
}

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int _currentIndex = 0;

  // الشاشات الخمسة (هيكل مبدئي)
  final List<Widget> _screens = [
    const Center(child: Text('الرئيسية - سيتم إضافة المهام والـ AI', style: TextStyle(fontSize: 20))),
    const Center(child: Text('المصحف الشريف', style: TextStyle(fontSize: 20))),
    const Center(child: Text('مواقيت الصلاة', style: TextStyle(fontSize: 20))),
    const Center(child: Text('الأذكار الذكية', style: TextStyle(fontSize: 20))),
    const Center(child: Text('بوصلة القبلة', style: TextStyle(fontSize: 20))),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _screens[_currentIndex],
      bottomNavigationBar: NavigationBar(
        selectedIndex: _currentIndex,
        onDestinationSelected: (index) {
          setState(() {
            _currentIndex = index;
          });
        },
        backgroundColor: const Color(0xFF0F172A),
        indicatorColor: const Color(0xFF38BDF8).withOpacity(0.3),
        destinations: const [
          NavigationDestination(icon: Icon(Icons.home), label: 'الرئيسية'),
          NavigationDestination(icon: Icon(Icons.menu_book), label: 'المصحف'),
          NavigationDestination(icon: Icon(Icons.access_time), label: 'الصلاة'),
          NavigationDestination(icon: Icon(Icons.format_list_bulleted), label: 'الأذكار'),
          NavigationDestination(icon: Icon(Icons.explore), label: 'القبلة'),
        ],
      ),
    );
  }
}
